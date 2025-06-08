package RightPanels;

import Designs.buttonStyler;
import Designs.comboBoxStyler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTaskPanel extends JDialog{
    private JButton saveButton;
    private JButton cancelButton;
    private JComboBox statusComboBox;
    private JComboBox priorityComboBox;
    private JComboBox categoryComboBox;
    private JTextField descriptionField;
    private JTextField nameField;
    private JPanel mainFrame;

    public AddTaskPanel(JFrame parent){
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
                //do zrobienia
                dispose();
            }
        });
    }
}
