import Designs.buttonStyler;
import Fonts.FontAwesome;
import Fonts.SimpleIcons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class RegisterMenu extends JFrame{
    private JPanel mainFrame;
    private JTextField loginField;
    private JPasswordField rPasswordField;
    private JButton registerButton;
    private JPasswordField passwordField;
    private JLabel iconLabel;
    private JLabel loginLabel;
    private JLabel rPassEye;
    private JLabel passEye;
    private char defaultEchoChar;
    private boolean[] isPassVisible = {false,false};


    private ImageIcon iconTM = new ImageIcon(getClass().getResource("Figures/TaskMaster.png"));

    private static ImageIcon resize(ImageIcon src, int width, int height){
        return new ImageIcon(src.getImage().getScaledInstance(width,height, Image.SCALE_SMOOTH));
    }

    public RegisterMenu() {
        super("Register Menu");
        this.setContentPane(mainFrame);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int width = 500, height = 600;
        this.setSize(width,height);

        buttonStyler.styleButton(registerButton);

        iconLabel.setIcon(resize(iconTM,150,150));

        passEye.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        rPassEye.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        passEye.setPreferredSize(new Dimension(16,16));
        rPassEye.setPreferredSize(new Dimension(16,16));
        FontAwesome.setLabelIcon(passEye, FontAwesome.Icons.EYE,16f);
        FontAwesome.setLabelIcon(rPassEye, FontAwesome.Icons.EYE,16f);
        defaultEchoChar = passwordField.getEchoChar();

        loginLabel.setForeground(UIColors.LINKCOLOR.getColor());
        Font originalFont = loginLabel.getFont();
        Map<TextAttribute, Object> attr = new HashMap<>(originalFont.getAttributes());
        attr.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        loginLabel.setFont(originalFont.deriveFont(attr));
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        this.getRootPane().setDefaultButton(registerButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String loginInput = loginField.getText();
                char[] passwordChars = rPasswordField.getPassword();
                char[] rPasswordChars = passwordField.getPassword();
                String passwordInput = new String(passwordChars);
                String rPasswordInput = new String(rPasswordChars);

                if (loginInput.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(RegisterMenu.this, "Pole loginu nie może być puste!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (passwordInput.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(RegisterMenu.this, "Pole hasła nie może być puste!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (rPasswordInput.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(RegisterMenu.this, "Pole powtórz hasło nie może być puste!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (loginInput.trim().length() < 3) {
                    JOptionPane.showMessageDialog(RegisterMenu.this, "Login musi mieć co najmniej 3 znaki!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (loginInput.trim().length() > 20) {
                    JOptionPane.showMessageDialog(RegisterMenu.this, "Login nie może mieć więcej niż 20 znaków!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Sprawdzenie czy login zawiera tylko dozwolone znaki
                if (!loginInput.trim().matches("^[a-zA-Z0-9_]+$")) {
                    JOptionPane.showMessageDialog(RegisterMenu.this, "Login może zawierać tylko litery, cyfry i podkreślenia!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Sprawdzenie minimalnej długości hasła
                if (passwordInput.length() < 6) {
                    JOptionPane.showMessageDialog(RegisterMenu.this, "Hasło musi mieć co najmniej 6 znaków!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Sprawdzenie czy hasła się zgadzają
                if (!passwordInput.equals(rPasswordInput)) {
                    JOptionPane.showMessageDialog(RegisterMenu.this, "Hasła nie są identyczne!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    rPasswordField.setText("");
                    passwordField.setText("");
                    rPasswordField.requestFocus();
                    return;
                }

                // Sprawdzenie czy hasło zawiera co najmniej jedną cyfrę
                if (!passwordInput.matches(".*\\d.*")) {
                    JOptionPane.showMessageDialog(RegisterMenu.this, "Hasło musi zawierać co najmniej jedną cyfrę!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Sprawdzenie czy hasło zawiera co najmniej jedną literę
                if (!passwordInput.matches(".*[a-zA-Z].*")) {
                    JOptionPane.showMessageDialog(RegisterMenu.this, "Hasło musi zawierać co najmniej jedną literę!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Sprawdzenie czy login nie jest już zajęty (tu można dodać sprawdzenie w bazie danych)
                if (loginInput.trim().equalsIgnoreCase("admin")) {
                    JOptionPane.showMessageDialog(RegisterMenu.this, "Ten login jest już zajęty!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(RegisterMenu.this, "Konto zostało utworzone pomyślnie!", "Sukces", JOptionPane.INFORMATION_MESSAGE);

                loginField.setText("");
                rPasswordField.setText("");
                passwordField.setText("");

                dispose();
                LoginMenu mainMenu = new LoginMenu();
                mainMenu.setVisible(true);
            }
        });
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
                LoginMenu mainMenu = new LoginMenu();
                mainMenu.setVisible(true);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                loginLabel.setForeground(UIColors.HLCOLOR.getColor());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginLabel.setForeground(UIColors.LINKCOLOR.getColor());
            }
        });
        passEye.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(isPassVisible[0]){
                    isPassVisible[0]=false;
                    passwordField.setEchoChar(defaultEchoChar);
                    FontAwesome.setLabelIcon(passEye, FontAwesome.Icons.EYE,16f);
                }else{
                    isPassVisible[0]=true;
                    passwordField.setEchoChar((char) 0);
                    FontAwesome.setLabelIcon(passEye, FontAwesome.Icons.EYE_SLASH,16f);
                }
            }
        });
        rPassEye.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(isPassVisible[1]){
                    isPassVisible[1]=false;
                    rPasswordField.setEchoChar(defaultEchoChar);
                    FontAwesome.setLabelIcon(rPassEye, FontAwesome.Icons.EYE,16f);
                }else{
                    rPasswordField.setEchoChar((char) 0);
                    isPassVisible[1]=true;
                    FontAwesome.setLabelIcon(rPassEye, FontAwesome.Icons.EYE_SLASH,16f);
                }
            }
        });
    }
}
