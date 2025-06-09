package RightPanels;

import Database.TaskDAO;
import Designs.buttonStyler;
import Designs.comboBoxStyler;
import Models.Task;
import Models.TaskPriority;
import Models.TaskStatus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;

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
                String description = descriptionField.getText().trim();
                String priorityString = (String) priorityComboBox.getSelectedItem();
                String statusString = (String) statusComboBox.getSelectedItem();

                TaskPriority priority = TaskPriority.fromDisplayName(priorityString);
                TaskStatus status = TaskStatus.fromDisplayName(statusString);

                if (priority == null) priority = TaskPriority.MEDIUM;
                if (status == null) status = TaskStatus.TODO;
                try{
                    Task newTask = new Task();
                    newTask.setTitle(title);
                    newTask.setDescription(description);
                    newTask.setPriority(priority);
                    newTask.setStatus(status);
                    newTask.setUserID(userID);
                    newTask.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                    boolean success = TaskDAO.saveTask(newTask);
                    if (success) {
                        JOptionPane.showMessageDialog(mainFrame,
                                "Zadanie '" + title + "' zostało dodane pomyślnie!",
                                "Sukces", JOptionPane.INFORMATION_MESSAGE);
                        dispose();

                    } else {
                        JOptionPane.showMessageDialog(mainFrame,
                                "Nie udało się zapisać zadania.",
                                "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                }catch (SQLException e) {
                    e.printStackTrace();
                    }


                if (title.isEmpty()) {
                    JOptionPane.showMessageDialog(AddTaskPanel.this,
                            "Tytuł zadania nie może być pusty!",
                            "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                dispose();
            }
        });
    }
}
