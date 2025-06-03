package www;

import java.util.Comparator;

/**
 * Complex comparator that orders books by author, then by publication year,
 * then by page count.
 */
public class AuthorYearPagesComparator implements Comparator<Book> {
    @Override
    public int compare(Book book1, Book book2) {
        // First compare by author
        int authorComparison = book1.getAuthor().compareTo(book2.getAuthor());
        if (authorComparison != 0) {
            return authorComparison;
        }

        // If authors are the same, compare by publication year
        int yearComparison = Integer.compare(book1.getPublicationYear(), book2.getPublicationYear());
        if (yearComparison != 0) {
            return yearComparison;
        }

        // If publication years are the same, compare by page count
        return Integer.compare(book1.getPageCount(), book2.getPageCount());
    }
}
