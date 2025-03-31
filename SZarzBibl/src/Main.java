import LibraryManagementSystem.*;
import Utils.Input;

public class Main {
    public static void main(String[] args) {
        Library lib = new Library();
        BookInput binput = new ConsoleBookInput();
        Input input = new Input();
        boolean isRunning = true;

        while(isRunning){
            System.out.println("\n--- MENU ---");
            System.out.println("1. Dodaj książkę");
            System.out.println("2. Usuń książkę");
            System.out.println("3. Edytuj książkę");
            System.out.println("4. Pokaż wszystkie książki");
            System.out.println("5. Wyszukaj książkę po ISBN");
            System.out.println("0. Wyjście");
            System.out.println("Wybierz opcję: ");

            String choice = input.InputString();
            try{
                switch(choice){
                    case "1":
                        Book book = binput.readBook();
                        lib.addBook(book);
                        break;
                    case "2":
                        System.out.println("Podaj ISBN do usunięcia: ");
                        lib.removeBook(input.InputString());
                        break;
                    case "3":
                        System.out.println("Podaj ISBN książki do edycji: ");
                        String isbn = input.InputString();
                        Book updatedBook = binput.readBook();
                        lib.editBook(isbn, updatedBook);
                        break;
                    case "4":
                        lib.listBooks();
                        break;
                    case "5":
                        System.out.println("Podaj ISBN do wyszukania: ");
                        lib.findBook(input.InputString());
                        break;
                    case "0":
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Nieprawidłowa opcja.");
                }
            }catch (InvalidBookException e){
                System.out.println("Błąd: " + e.getMessage());
            }
        }
    }
}