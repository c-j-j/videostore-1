public class Rental {
    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public String getTitle() {
        return movie.getTitle();
    }

    int determineFrequentRenterPoints() {

        int frequentRenterPoints = 1;
        if (movie.getPriceCode() == Movie.NEW_RELEASE
                && daysRented > 1)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }

    double determineAmount() {
        return movie.determineMovieAmount(daysRented);
    }

}
