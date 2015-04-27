public class NewReleaseMovie extends Movie {
    public NewReleaseMovie(String title) {
        super(title, Movie.NEW_RELEASE);
    }

    double determineMovieAmount(int daysRented) {
        return (double) (daysRented * 3);
    }

    int determineFrequentRenterPoints(int daysRented) {
        int frequentRenterPoints = 1;
        if (daysRented > 1)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }
}
