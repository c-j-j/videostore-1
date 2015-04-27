public class RegularMovie extends Movie {
    public RegularMovie(String title) {
        super(title, Movie.REGULAR);
    }

    double determineMovieAmount(int daysRented) {
        double thisAmount = 2;
        if (daysRented > 2)
            thisAmount += (daysRented - 2) * 1.5;

        return thisAmount;
    }

    int determineFrequentRenterPoints(int daysRented) {
        int frequentRenterPoints = 1;
        if (priceCode == NEW_RELEASE
                && daysRented > 1)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }
}
