package RightPanels;

import Database.CategoryDAO;
import Designs.buttonStyler;
import Designs.comboBoxStyler;
import Fonts.FontAwesome;
import Models.Category;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoriesPanel extends JPanel {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JComboBox filterComboBox;
    private JTextField searchField;
    private JButton addCategoryButton;
    private JTable categoriesTable;

    private CategoryTableModel tableModel;
    private int userID;
    private List<Category> allCategories;

    private static class CategoryTableModel extends AbstractTableModel {
        private final String[] columnNames = {"Nazwa", "Liczba zadań"};
        private List<Category> categories = new ArrayList<>();
        private List<Integer> taskCounts = new ArrayList<>();

        public void setCategories(List<Category> categories) {
            this.categories = categories != null ? categories : new ArrayList<>();
            loadTaskCounts();
            fireTableDataChanged();
        }

        private void loadTaskCounts() {
            taskCounts.clear();
            for (Category category : categories) {
                try {
                    int count = CategoryDAO.getTaskCountForCategory(category.getId());
                    taskCounts.add(count);
                } catch (SQLException e) {
                    taskCounts.add(0);
                }
            }
        }

        @Override
        public int getRowCount() {
            return categories.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (rowIndex >= categories.size()) return null;

            Category category = categories.get(rowIndex);
            switch (columnIndex) {
                case 0: return category.getName();
                case 1: return taskCounts.get(rowIndex);
                default: return null;
            }
        }

        public Category getCategoryAt(int rowIndex) {
            return (rowIndex >= 0 && rowIndex < categories.size()) ? categories.get(rowIndex) : null;
        }

        public int getTaskCountAt(int rowIndex) {
            return (rowIndex >= 0 && rowIndex < taskCounts.size()) ? taskCounts.get(rowIndex) : 0;
        }
    }

    public CategoriesPanel(int userID) {
        this.userID = userID;
        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);

        setupComponents();
        setupEventListeners();
        loadCategories();
    }

    public CategoriesPanel() {
        this(1);
    }

    private void setupComponents() {
        buttonStyler.styleButton(addCategoryButton, FontAwesome.Icons.PLUS, " Dodaj kategorię");
        addCategoryButton.setBackground(new Color(29, 78, 216));
        addCategoryButton.setForeground(Color.WHITE);

        filterComboBox.removeAllItems();
        filterComboBox.addItem("Wszystkie");
        filterComboBox.addItem("Z zadaniami");
        filterComboBox.addItem("Bez zadań");
        comboBoxStyler.styleComboBox(filterComboBox);

        tableModel = new CategoryTableModel();
        categoriesTable.setModel(tableModel);

        categoriesTable.setRowHeight(35);
        categoriesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        categoriesTable.getTableHeader().setReorderingAllowed(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        categoriesTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        categoriesTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
    }

    private void setupEventListeners() {
        addCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //showAddCategoryDialog();
            }
        });

        // Filtrowanie kategorii
        filterComboBox.addActionListener(e -> filterCategories());

        // Wyszukiwanie kategorii
        searchField.addActionListener(e -> filterCategories());

        // Obsługa podwójnego kliknięcia w tabelę - edycja kategorii
        categoriesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = categoriesTable.getSelectedRow();
                    if (row >= 0) {
                        Category category = tableModel.getCategoryAt(row);
                        if (category != null) {
                           // showEditCategoryDialog(category);
                        }
                    }
                }
            }
        });
    }

    private void loadCategories() {
        try {
            allCategories = CategoryDAO.getCategoriesByUserId(userID);
            filterCategories();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Błąd podczas ładowania kategorii: " + e.getMessage(),
                    "Błąd", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void filterCategories() {
        if (allCategories == null) return;

        List<Category> filteredCategories = new ArrayList<>(allCategories);

        String selectedFilter = (String) filterComboBox.getSelectedItem();
        if ("Z zadaniami".equals(selectedFilter)) {
            filteredCategories = filteredCategories.stream()
                    .filter(category -> {
                        try {
                            return CategoryDAO.getTaskCountForCategory(category.getId()) > 0;
                        } catch (SQLException e) {
                            return false;
                        }
                    })
                    .collect(Collectors.toList());
        } else if ("Bez zadań".equals(selectedFilter)) {
            filteredCategories = filteredCategories.stream()
                    .filter(category -> {
                        try {
                            return CategoryDAO.getTaskCountForCategory(category.getId()) == 0;
                        } catch (SQLException e) {
                            return true;
                        }
                    })
                    .collect(Collectors.toList());
        }

        String searchText = searchField.getText().trim().toLowerCase();
        if (!searchText.isEmpty()) {
            filteredCategories = filteredCategories.stream()
                    .filter(category -> category.getName().toLowerCase().contains(searchText))
                    .collect(Collectors.toList());
        }

        tableModel.setCategories(filteredCategories);
    }

    public void refreshCategories() {
        loadCategories();
    }
}