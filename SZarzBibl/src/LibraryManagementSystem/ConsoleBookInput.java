package LibraryManagementSystem;

import Utils.Input;

public class ConsoleBookInput implements BookInput{
    Input input = new Input();
    @Override
    public Book readBook() throws InvalidBookException{
        Book book = new Book(null,null,null);
        System.out.println("Podaj tytuł: ");
        String title = input.InputLine();
        book.setTitle(title);
        System.out.println("Podaj autora: ");
        String author = input.InputLine();
        book.setAuthor(author);
        System.out.println("Podaj ISBN (13 cyfr): ");
        String isbn = input.InputString();
        book.setIsbn(isbn);
        return new Book(title,author,isbn);
    }
}
