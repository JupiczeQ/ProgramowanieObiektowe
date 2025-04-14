import RunInput.Input;

import java.util.HashMap;
import java.util.Map;

public class zad2 {
    Input input = new Input();
    private Map<String, String> data = new HashMap<>();

    public zad2(){
        while(true){
            System.out.println("Co chciałbyś zrobić? ");
            System.out.println("1. dodaj uzytkownika");
            System.out.println("2. Zaloguj sie");
            System.out.println("3. wyjscie");
            int opt = input.inputInt();
            switch (opt){
                case 1:
                    addUser();
                    break;
                case 2:
                    logIn();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Nieprawidlowa opcja.");
            }
        }
    }

    public void addUser(){
        System.out.println("Podaj login: ");
        String login = input.inputString();
        System.out.println("Podaj hasło: ");
        String haslo = input.inputString();
        if(data.containsKey(login)) {
            System.out.println("Użytkownik o podanym loginie juz istnieje.");
        }else{
            data.put(login, haslo);
            System.out.println("Użytkownik został zarejestrowany.");
        }
    }

    public void logIn(){
        System.out.println("Podaj login: ");
        String login = input.inputString();
        System.out.println("Podaj hasło: ");
        String haslo = input.inputString();
        if(data.containsKey(login)){
            if(data.get(login).equals(haslo)){
                System.out.println("Zalogowano pomyślnie.");
            }else{
                System.out.println("Hasło nieprawidłowe.");
            }
        }else{
            System.out.println("Użytkownik o podanym loginie nie istnieje.");
        }
    }
}
