import Database.UserDAO;
import Designs.buttonStyler;
import Fonts.FontAwesome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LoginMenu extends JFrame{
    private JPanel mainFrame;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JLabel iconLabel;
    private JLabel registerLabel;
    private JButton loginButton;
    private JLabel passEye;

    private char defaultEchoChar;
    private boolean isPassVisible = false;

    private ImageIcon iconTM = new ImageIcon(getClass().getResource("Figures/TaskMaster.png"));

    private static final String LOGIN = "admin";
    private static final String PASSWORD = "admin";

    private static ImageIcon resize(ImageIcon src, int width, int height){
        return new ImageIcon(src.getImage().getScaledInstance(width,height, Image.SCALE_SMOOTH));
    }

    public LoginMenu() {
        super("Menu");
        this.setContentPane(mainFrame);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int width = 500, height = 500;
        this.setSize(width,height);

        buttonStyler.styleButton(loginButton);

        iconLabel.setIcon(resize(iconTM,150,150));

        passEye.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        passEye.setPreferredSize(new Dimension(16,16));
        FontAwesome.setLabelIcon(passEye, FontAwesome.Icons.EYE,16f);
        defaultEchoChar = passwordField.getEchoChar();

        registerLabel.setForeground(UIColors.LINKCOLOR.getColor());
        Font originalFont = registerLabel.getFont();
        Map<TextAttribute, Object> attr = new HashMap<>(originalFont.getAttributes());
        attr.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        registerLabel.setFont(originalFont.deriveFont(attr));
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        this.getRootPane().setDefaultButton(loginButton); // przycisk 'enter' wciska loginButton

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String loginInput = loginField.getText();
                char[] passwordChars = passwordField.getPassword();
                String passwordInput = new String(passwordChars);

                if (loginInput.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(LoginMenu.this, "Pole loginu nie może być puste!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (passwordInput.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(LoginMenu.this, "Pole hasła nie może być puste!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (loginInput.trim().equals(LOGIN) && passwordInput.equals(PASSWORD)) {
                    JOptionPane.showMessageDialog(LoginMenu.this, "Zalogowano jako admin", "Sukces", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    Dashboard dashboard = new Dashboard(1);
                    dashboard.setVisible(true);
                }
                try {
                    int userID = UserDAO.authenticateUser(loginInput.trim(),passwordInput.trim());
                    if (userID!=-1){
                        dispose();
                        Dashboard dashboard = new Dashboard(userID);
                        dashboard.setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(LoginMenu.this, "Nieprawidłowy login lub hasło!", "Błąd logowania", JOptionPane.ERROR_MESSAGE);
                        //loginField.setText("");
                        passwordField.setText("");
                        loginField.requestFocus();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
                RegisterMenu regMenu = new RegisterMenu();
                regMenu.setVisible(true);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                registerLabel.setForeground(UIColors.HLCOLOR.getColor());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                registerLabel.setForeground(UIColors.LINKCOLOR.getColor());
            }
        });

        passEye.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(isPassVisible){
                    isPassVisible = false;
                    passwordField.setEchoChar(defaultEchoChar);
                    FontAwesome.setLabelIcon(passEye, FontAwesome.Icons.EYE, 16f);
                } else {
                    isPassVisible = true;
                    passwordField.setEchoChar((char) 0);
                    FontAwesome.setLabelIcon(passEye, FontAwesome.Icons.EYE_SLASH, 16f);
                }
            }
        });
    }
}