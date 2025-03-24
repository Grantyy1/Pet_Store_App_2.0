package library.inventory;

public class Book extends Item {
    private String author;
    private Genre genre;

    public Book(String title, String dateReceived, String author, Genre genre) throws Exception {
        super(title, dateReceived);
        setAuthor(author);
        setGenre(genre);
    }

    public Book(int id, String title, String dateReceived, String author, Genre genre) throws Exception {
        super(id, title, dateReceived);
        setAuthor(author);
        setGenre(genre);
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) throws Exception {
        author = author.trim();

        if (author.isBlank())
            throw new Exception("Invalid data! Author can not be empty.");

        this.author = author;
    }

    public Genre getGenre() { return genre; }

    public void setGenre(Genre genre) { this.genre = genre; }

    @Override
    public void displayItem(){
        super.displayItem();
        System.out.printf(" %-15s %-10s\n", author, genre);
    }
}
