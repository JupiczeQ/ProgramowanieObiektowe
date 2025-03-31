package LibraryManagementSystem;

import java.util.HashMap;
import java.util.Map;

public class Library {
    private Map<String, Book> books = new HashMap<>();

    public void addBook(Book book){
        if(books.containsKey(book.getIsbn())){
            System.out.println("W systemie znajduje się już książka z podanym ISBN");
            return;
        }
        books.put(book.getIsbn(), book);
        System.out.println("Książka została dodana.");
    }

    public void removeBook(String isbn){
        if(books.remove(isbn)!=null){
            System.out.println("Książka została usunięta.");
        }else{
            System.out.println("Nie znaleziono książki.");
        }
    }

    public void editBook(String isbn, Book updatedBook){
        if(books.containsKey(isbn)){
            books.put(isbn, updatedBook);
            System.out.println("Książka została zaktualizowana.");
        }else{
            System.out.println("Nie znaleziono książki.");
        }
    }

    public void listBooks(){
        if(books.isEmpty()){
            System.out.println("Brak książek w bibliotece.");
        }else {
            for(Book book : books.values()){
                System.out.println(book.toString());
            }
        }
    }

    public void findBook(String isbn){
        Book book = books.get(isbn);
        if(book != null){
            System.out.println(book.toString());
        }else{
            System.out.println("Nie znaleziono książki.");
        }
    }
}
