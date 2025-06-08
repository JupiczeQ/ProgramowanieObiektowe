import Designs.buttonStyler;
import Fonts.FontAwesome;
import RightPanels.CalendarPanel;
import RightPanels.CategoriesPanel;
import RightPanels.ProjectsPanel;
import RightPanels.TasksPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Dashboard extends JFrame{
    private JPanel mainFrame;
    private JButton tasksButton;
    private JButton projectsButton;
    private JButton categoriesButton;
    private JButton calendarButton;
    private JTextField searchField;
    private JLabel iconLabel;
    private JLabel notificationLabel;
    private JLabel settingsLabel;
    private JLabel userLabel;
    private JLabel signOutLabel;
    private JLabel searchLabel;
    private JPanel rightPanel;
    private TasksPanel tasksPanel;
    private ProjectsPanel projectsPanel;
    private CategoriesPanel categoriesPanel;
    private CalendarPanel calendarPanel;
    private CardLayout cardLayout;
    private JButton activeButton;
    private static final Color LIGHT_BLUE = new Color(219, 234, 254);

    private ImageIcon iconTM = new ImageIcon(getClass().getResource("Figures/TaskMaster.png"));

    private static ImageIcon resize(ImageIcon src, int width, int height){
        return new ImageIcon(src.getImage().getScaledInstance(width,height, Image.SCALE_SMOOTH));
    }

    private void createPanels(){
        cardLayout = new CardLayout();
        rightPanel.setLayout(cardLayout);

        tasksPanel = new TasksPanel();
        projectsPanel = new ProjectsPanel();
        categoriesPanel = new CategoriesPanel();
        calendarPanel = new CalendarPanel();

        rightPanel.add(tasksPanel, "TASKS");
        rightPanel.add(projectsPanel, "PROJECTS");
        rightPanel.add(categoriesPanel, "CATEGORIES");
        rightPanel.add(calendarPanel, "CALENDAR");

        cardLayout.show(rightPanel, "TASKS");

        activeButton = tasksButton;
    }

    private void setActiveButton(JButton button, String panelName) {
        if (activeButton != null) {
            activeButton.setBackground(Color.WHITE);
        }

        button.setBackground(LIGHT_BLUE);
        activeButton = button;

        cardLayout.show(rightPanel, panelName);

        button.repaint();
    }

    private void setupButtonListeners(){
        tasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setActiveButton(tasksButton, "TASKS");
            }
        });

        projectsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setActiveButton(projectsButton, "PROJECTS");
            }
        });

        categoriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setActiveButton(categoriesButton, "CATEGORIES");
            }
        });

        calendarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setActiveButton(calendarButton, "CALENDAR");
            }
        });
    }

    private void setupDesigns(){
        searchLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        notificationLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        settingsLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        userLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signOutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        buttonStyler.styleButton(tasksButton,FontAwesome.Icons.CHECK," Moje\nzadania");
        buttonStyler.styleButton(projectsButton,FontAwesome.Icons.FOLDER," Projekty");
        buttonStyler.styleButton(categoriesButton,FontAwesome.Icons.FILTER," Kategorie");
        buttonStyler.styleButton(calendarButton,FontAwesome.Icons.CALENDAR_ALT," Kalendarz");

        tasksButton.setBackground(LIGHT_BLUE);

        FontAwesome.setLabelIcon(searchLabel, FontAwesome.Icons.SEARCH, 16f);
        FontAwesome.setLabelIcon(notificationLabel, FontAwesome.Icons.BELL, 16f);
        FontAwesome.setLabelIcon(settingsLabel, FontAwesome.Icons.COG, 16f);
        FontAwesome.setLabelIcon(userLabel, FontAwesome.Icons.USER, 16f);
        userLabel.setText(buttonStyler.createMixedText(userLabel.getText()," testUser")); //DO ZMIANY
        FontAwesome.setLabelIcon(signOutLabel, FontAwesome.Icons.SIGN_OUT_ALT, 16f);
    }

    public Dashboard(){
        super("DashBoard");
        this.setContentPane(mainFrame);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int width = 1000, height = 800;
        this.setSize(width,height);

        createPanels();
        setupButtonListeners();
        setupDesigns();

        iconLabel.setIcon(resize(iconTM,150,150));

        signOutLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int result = JOptionPane.showConfirmDialog(
                        null,
                        "Czy na pewno chcesz się wylogować?",
                        "Potwierdzenie wylogowania",
                        JOptionPane.YES_NO_OPTION
                );
                if(result == JOptionPane.YES_OPTION){
                    dispose();
                    LoginMenu loginMenu = new LoginMenu();
                    loginMenu.setVisible(true);
                }
            }
        });
    }
}