package www;

/**
 * Represents a book with its basic information.
 * Implements Comparable to allow natural ordering by title.
 */
public class Book implements Comparable<Book> {
    private String isbn;
    private String title;
    private String author;
    private int publicationYear;
    private int pageCount;
    private double rating;

    /**
     * Creates a new de.library.www.Book with all its attributes.
     *
     * @param isbn           The ISBN number of the book
     * @param title          The title of the book
     * @param author         The author of the book
     * @param publicationYear The year when the book was published
     * @param pageCount      The number of pages in the book
     * @param rating         The rating of the book (0.0 to 5.0)
     */
    public Book(String isbn, String title, String author, int publicationYear, int pageCount, double rating) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.pageCount = pageCount;
        this.rating = rating;
    }

    // Getters and setters
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Implements the natural ordering of books by title.
     *
     * @param other The book to compare with
     * @return negative if this book comes before other, positive if after, 0 if equal
     */
    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.title);
    }

    /**
     * Returns a string representation of the book.
     *
     * @return A string with all book details
     */
    @Override
    public String toString() {
        return String.format("%s by %s (%d) - %d pages, rating: %.1f, ISBN: %s",
                title, author, publicationYear, pageCount, rating, isbn);
    }
}
