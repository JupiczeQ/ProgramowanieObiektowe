package LibraryManagementSystem;

public class Book  {
    private String title;
    private String author;
    private String isbn;

    public Book(String title, String author, String isbn) throws InvalidBookException{
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws InvalidBookException {
        if(title == null || title.trim().isEmpty()){
            throw new InvalidBookException("Tytuł nie moze być pusty.");
        }
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) throws InvalidBookException {
        if(author == null || author.trim().isEmpty()){
            throw new InvalidBookException("Autor nie moze być pusty.");
        }
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) throws InvalidBookException {
        if(isbn == null || isbn.trim().isEmpty() || isbn.length() != 13){
            throw new InvalidBookException("ISBN musi mieć 13 znaków.");
        }
        this.isbn = isbn;
    }

    @Override
    public String toString(){
        return "Tytuł: " + title + ", Autor: " + author + ", ISBN: " + isbn;
    }
}
