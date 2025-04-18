package filmtools;

public class FilmUtils {
    public static double calculateAverageOfRatings(double[] movieRatings) {
        double sum = 0;
        for (double rating : movieRatings) {
            sum += rating;
        }
        return sum / movieRatings.length;
    }
}