package www;

/**
 * de.library.www.Main class to demonstrate the library management functionality.
 */
public class Main {
    public static void main(String[] args) {
        // Create a new library
        Library library = new Library();

        // Add several books with different attributes
        library.addBook(new Book("978-0451524935", "1984", "George Orwell", 1949, 328, 4.7));
        library.addBook(new Book("978-0061120084", "To Kill a Mockingbird", "Harper Lee", 1960, 281, 4.8));
        library.addBook(new Book("978-0141439518", "Pride and Prejudice", "Jane Austen", 1813, 416, 4.6));
        library.addBook(new Book("978-0743273565", "The Great Gatsby", "F. Scott Fitzgerald", 1925, 180, 4.2));
        library.addBook(new Book("978-0307474278", "The Road", "Cormac McCarthy", 2006, 287, 4.0));
        library.addBook(new Book("978-0307277671", "The Alchemist", "Paulo Coelho", 1988, 208, 4.3));
        library.addBook(new Book("978-0618640157", "The Lord of the Rings", "J.R.R. Tolkien", 1954, 1178, 4.9));
        library.addBook(new Book("978-0553296983", "Brave New World", "Aldous Huxley", 1932, 268, 4.1));
        library.addBook(new Book("978-1400033416", "The Corrections", "Jonathan Franzen", 2001, 576, 3.8));
        library.addBook(new Book("978-0060935467", "To the Lighthouse", "Virginia Woolf", 1927, 209, 3.9));
        library.addBook(new Book("978-0060934347", "The Hobbit", "J.R.R. Tolkien", 1937, 366, 4.7));

        // Display all books (unsorted)
        System.out.println("Books in original order:");
        library.displayBooks();

        // Sort and display books by title (natural order)
        library.sortByTitle();
        library.displayBooks();

        // Sort and display books by author
        library.sortByAuthor();
        library.displayBooks();

        // Sort and display books by publication year
        library.sortByPublicationYear();
        library.displayBooks();

        // Sort and display books by rating (ascending)
        library.sortByRating(false);
        library.displayBooks();

        // Sort and display books by rating (descending)
        library.sortByRating(true);
        library.displayBooks();

        // Sort and display books by author, then year, then pages
        library.sortByAuthorYearAndPages();
        library.displayBooks();
    }
}
