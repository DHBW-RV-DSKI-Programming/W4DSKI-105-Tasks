package www;

import java.util.Comparator;

/**
 * Comparator that orders books by rating, with option for descending order.
 */
public class RatingComparator implements Comparator<Book> {
    private boolean descending;

    /**
     * Creates a rating comparator.
     *
     * @param descending If true, sorts from highest to lowest rating
     */
    public RatingComparator(boolean descending) {
        this.descending = descending;
    }

    @Override
    public int compare(Book book1, Book book2) {
        // Use Double.compare for proper handling of double values
        int result = Double.compare(book1.getRating(), book2.getRating());

        // If descending is requested, reverse the comparison result
        return descending ? -result : result;
    }
}
