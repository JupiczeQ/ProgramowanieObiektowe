package RightPanels;

import Designs.buttonStyler;
import Designs.comboBoxStyler;
import Fonts.FontAwesome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TasksPanel extends JPanel{
    private JPanel rightPanel;
    private JLabel sidePanelName;
    private JComboBox statusComboBox;
    private JComboBox priorityComboBox;
    private JButton addTaskButton;
    private JTable tasksTable;
    private JPanel mainFrame;
    private JFrame parentFrame;

    public TasksPanel(){
        this.setLayout(new BorderLayout());
        this.add(rightPanel, BorderLayout.CENTER);

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
                AddTaskPanel addTaskPanel = new AddTaskPanel(parentFrame);
                addTaskPanel.setVisible(true);
                addTaskPanel.setAutoRequestFocus(true);
            }
        });
    }
}