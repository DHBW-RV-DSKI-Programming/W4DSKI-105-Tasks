package www;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages a collection of books with various sorting capabilities.
 */
public class Library {
    private List<Book> books;

    /**
     * Creates a new empty library.
     */
    public Library() {
        books = new ArrayList<>();
    }

    /**
     * Adds a book to the library collection.
     *
     * @param book The book to add
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Displays all books in the current sorting order.
     */
    public void displayBooks() {
        System.out.println("\nLibrary Collection:");
        System.out.println("------------------");
        if (books.isEmpty()) {
            System.out.println("The library is empty.");
        } else {
            for (int i = 0; i < books.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, books.get(i));
            }
        }
        System.out.println();
    }

    /**
     * Sorts the books by title (uses the natural order from Comparable).
     */
    public void sortByTitle() {
        Collections.sort(books);
        System.out.println("Books sorted by title");
    }

    /**
     * Sorts the books by author name.
     */
    public void sortByAuthor() {
        Collections.sort(books, new AuthorComparator());
        System.out.println("Books sorted by author");
    }

    /**
     * Sorts the books by publication year.
     */
    public void sortByPublicationYear() {
        Collections.sort(books, new PublicationYearComparator());
        System.out.println("Books sorted by publication year");
    }

    /**
     * Sorts the books by rating, optionally in descending order.
     *
     * @param descending If true, sorts from highest to lowest rating
     */
    public void sortByRating(boolean descending) {
        RatingComparator comparator = new RatingComparator(descending);
        Collections.sort(books, comparator);
        System.out.println("Books sorted by rating " + (descending ? "(descending)" : "(ascending)"));
    }

    /**
     * Sorts the books by author, then by year, then by page count.
     */
    public void sortByAuthorYearAndPages() {
        Collections.sort(books, new AuthorYearPagesComparator());
        System.out.println("Books sorted by author, year, and pages");
    }
}
