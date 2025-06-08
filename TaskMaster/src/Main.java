import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        setupPolishUI();

        //Dashboard menu = new Dashboard();
        LoginMenu menu = new LoginMenu();
        //RegisterMenu menu = new RegisterMenu();
        menu.setVisible(true);
    }
    private static void setupPolishUI() {
        UIManager.put("OptionPane.okButtonText", "OK");
        UIManager.put("OptionPane.yesButtonText", "Tak");
        UIManager.put("OptionPane.noButtonText", "Nie");
        UIManager.put("OptionPane.cancelButtonText", "Anuluj");

        UIManager.put("FileChooser.openButtonText", "Otwórz");
        UIManager.put("FileChooser.cancelButtonText", "Anuluj");
        UIManager.put("FileChooser.saveButtonText", "Zapisz");
    }
}