package RightPanels;

import Database.TaskDAO;
import Designs.buttonStyler;
import Designs.comboBoxStyler;
import Fonts.FontAwesome;
import Models.Task;
import Models.TaskPriority;
import Models.TaskStatus;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TasksPanel extends JPanel{
    private JPanel rightPanel;
    private JLabel sidePanelName;
    private JComboBox statusComboBox;
    private JComboBox priorityComboBox;
    private JButton addTaskButton;
    private JTable tasksTable;
    private JPanel mainFrame;
    private JFrame parentFrame;
    private int userID;
    private TaskTableModel tableModel;

    private static class TaskTableModel extends AbstractTableModel {
        private final String[] columnNames = {"Tytuł", "Status", "Priorytet", "Kategoria", "Data utworzenia"};
        private List<Task> tasks = new ArrayList<>();
        private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        public void setTasks(List<Task> tasks) {
            this.tasks = tasks != null ? tasks : new ArrayList<>();
            fireTableDataChanged();
        }

        @Override
        public int getRowCount() {
            return tasks.size();
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
            if (rowIndex >= tasks.size()) return null;

            Task task = tasks.get(rowIndex);
            switch (columnIndex) {
                case 0: return task.getTitle();
                case 1: return task.getStatus();
                case 2: return task.getPriority();
                case 3: return task.getCategory() != null ? task.getCategory().getName() : "Brak kategorii";
                case 4: return task.getCreatedAt() != null ? dateFormat.format(task.getCreatedAt()) : "";
                default: return null;
            }
        }

        public Task getTaskAt(int rowIndex) {
            if (rowIndex >= 0 && rowIndex < tasks.size()) {
                return tasks.get(rowIndex);
            }
            return null;
        }
    }

    private void filterTasks() {
        Object selectedStatusObj = statusComboBox.getSelectedItem();
        Object selectedPriorityObj = priorityComboBox.getSelectedItem();

        TaskStatus selectedStatus = null;
        TaskPriority selectedPriority = null;

        if (selectedStatusObj instanceof TaskStatus) {
            selectedStatus = (TaskStatus) selectedStatusObj;
        }

        if (selectedPriorityObj instanceof TaskPriority) {
            selectedPriority = (TaskPriority) selectedPriorityObj;
        }

        try {
            List<Task> tasks = TaskDAO.getTasksByUserId(userID, selectedStatus, selectedPriority);
            tableModel.setTasks(tasks);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Błąd podczas filtrowania zadań: " + e.getMessage(),
                    "Błąd", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private static class TaskTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (!isSelected) {
                TaskTableModel model = (TaskTableModel) table.getModel();
                Task task = model.getTaskAt(row);

                if (task != null) {
                    // Kolory dla statusu
                    switch (task.getStatus()) {
                        case TODO:
                            setBackground(new Color(254, 242, 242)); // Jasny czerwony
                            break;
                        case IN_PROGRESS:
                            setBackground(new Color(255, 251, 235)); // Jasny żółty
                            break;
                        case COMPLETED:
                            setBackground(new Color(240, 253, 244)); // Jasny zielony
                            break;
                        default:
                            setBackground(Color.WHITE);
                    }

                    // Kolor dla priorytetu
                    switch (task.getPriority()) {
                        case HIGH:
                            setForeground(new Color(185, 28, 28)); // Czerwony tekst
                            break;
                        case MEDIUM:
                            setForeground(new Color(161, 98, 7)); // Pomarańczowy tekst
                            break;
                        case LOW:
                            setForeground(new Color(21, 128, 61)); // Zielony tekst
                            break;
                    }
                }
            } else {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            }

            return this;
        }
    }

    public void loadTasks() {
        try {
            List<Task> tasks = TaskDAO.getTasksByUserId(userID);
            tableModel.setTasks(tasks);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Błąd podczas ładowania zadań: " + e.getMessage(),
                    "Błąd", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public TasksPanel(int userID){
        this.setLayout(new BorderLayout());
        this.add(rightPanel, BorderLayout.CENTER);

        this.userID = userID;

        statusComboBox.removeAllItems();
        statusComboBox.addItem("Wszystkie");
        for (TaskStatus status : TaskStatus.values()) {
            statusComboBox.addItem(status);
        }

        priorityComboBox.removeAllItems();
        priorityComboBox.addItem("Wszystkie");
        for (TaskPriority priority : TaskPriority.values()) {
            priorityComboBox.addItem(priority);
        }

        tableModel = new TaskTableModel();
        loadTasks();

        tasksTable.setModel(tableModel);
        tasksTable.setRowHeight(30);
        tasksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tasksTable.getTableHeader().setReorderingAllowed(false);

        // Renderery dla kolorów
        tasksTable.setDefaultRenderer(Object.class, new TaskTableCellRenderer());

        comboBoxStyler.styleComboBox(statusComboBox);
        comboBoxStyler.styleComboBox(priorityComboBox);

        buttonStyler.styleButton(addTaskButton, FontAwesome.Icons.PLUS, " Dodaj zadanie");
        addTaskButton.setBackground(new Color(29, 78, 216));
        addTaskButton.setForeground(Color.WHITE);

        Window parentWindow = SwingUtilities.getWindowAncestor(mainFrame);
        if(parentWindow instanceof JFrame){
            parentFrame = (JFrame) parentWindow;
        }

        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddTaskPanel addTaskPanel = new AddTaskPanel(parentFrame, userID);

                addTaskPanel.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e){
                        loadTasks();
                    }
                });
                addTaskPanel.setVisible(true);
                addTaskPanel.setAutoRequestFocus(true);
            }
        });
        statusComboBox.addActionListener(e -> filterTasks());
        priorityComboBox.addActionListener(e -> filterTasks());
    }
}