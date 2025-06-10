package RightPanels;

import Database.CategoryDAO;
import Database.TaskDAO;
import Designs.buttonStyler;
import Designs.comboBoxStyler;
import Models.Category;
import Models.Task;
import Models.TaskPriority;
import Models.TaskStatus;
import Utils.MessageUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AddTaskPanel extends JDialog{
    private JButton saveButton;
    private JButton cancelButton;
    private JComboBox statusComboBox;
    private JComboBox priorityComboBox;
    private JComboBox categoryComboBox;
    private JTextArea descriptionField;
    private JTextField nameField;
    private JPanel mainFrame;
    private int userID;
    private List<Category> categories = new ArrayList<>();

    public AddTaskPanel(JFrame parent, int userID){
        super(parent,"Dodaj zadanie", true);
        this.setContentPane(mainFrame);
        int width = 500, height = 400;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(width,height);
        this.setLocationRelativeTo(parent);
        this.setResizable(false);

        comboBoxStyler.styleComboBox(statusComboBox);
        comboBoxStyler.styleComboBox(priorityComboBox);
        comboBoxStyler.styleComboBox(categoryComboBox);

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

        try {
            categories = CategoryDAO.getCategoriesByUserId(userID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Category cat : categories){
            categoryComboBox.addItem(cat.getName());
        }

        descriptionField.setBorder(UIManager.getBorder("TextField.border"));

        buttonStyler.styleButton(saveButton);
        buttonStyler.styleButton(cancelButton);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String title = nameField.getText().trim();

                if (title.isEmpty()) {
                    MessageUtils.showError(AddTaskPanel.this, "Tytuł zadania nie może być pusty!");
                    return;
                }

                String description = descriptionField.getText().trim();

                Object selectedPriority = priorityComboBox.getSelectedItem();
                Object selectedStatus = statusComboBox.getSelectedItem();
                String selectedCategoryName = (String) categoryComboBox.getSelectedItem();
                Integer categoryId = null;

                TaskPriority priority = TaskPriority.MEDIUM; // domyślne
                TaskStatus status = TaskStatus.TODO; // domyślne

                // Sprawdź czy to enum czy String "Wszystkie"
                if (selectedPriority instanceof TaskPriority) {
                    priority = (TaskPriority) selectedPriority;
                }

                if (selectedStatus instanceof TaskStatus) {
                    status = (TaskStatus) selectedStatus;
                }

                try {
                    if (selectedCategoryName != null && !selectedCategoryName.equals("Wszystkie")) {
                        for (Category cat : categories) {
                            if (cat.getName().equals(selectedCategoryName)) {
                                categoryId = cat.getId();
                                break;
                            }
                        }
                    }

                    Task newTask = new Task();
                    newTask.setTitle(title);
                    newTask.setDescription(description);
                    newTask.setPriority(priority);
                    newTask.setStatus(status);
                    newTask.setUserID(userID);
                    newTask.setCategoryId(categoryId);
                    newTask.setCreatedAt(new Timestamp(System.currentTimeMillis()));

                    boolean success = TaskDAO.saveTask(newTask);
                    if (success) {
                        MessageUtils.showSuccess(AddTaskPanel.this,"Zadanie '" + title + "' zostało dodane pomyślnie!");
                        dispose();
                    } else {
                        MessageUtils.showError(AddTaskPanel.this, "Nie udało się zapisać zadania.");
                    }
                } catch (SQLException e) {
                    MessageUtils.showDatabaseError(AddTaskPanel.this, e);
                }
            }
        });
    }
}
