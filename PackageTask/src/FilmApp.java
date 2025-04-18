import filmtools.FilmUtils;

public class FilmApp {
    public static void main(String[] args) {
        double[] ratings = {8.5, 7.0, 9.0};
        double avgRating = FilmUtils.calculateAverageOfRatings(ratings);
        System.out.println("Die durchschnittliche Bewertung des Films ist: " + avgRating);
    }
}