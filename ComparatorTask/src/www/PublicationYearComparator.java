package www;

import java.util.Comparator;

/**
 * Comparator that orders books by publication year.
 */
public class PublicationYearComparator implements Comparator<Book> {
    @Override
    public int compare(Book book1, Book book2) {
        return Integer.compare(book1.getPublicationYear(), book2.getPublicationYear());
    }
}
