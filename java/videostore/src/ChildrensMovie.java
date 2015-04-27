public class ChildrensMovie extends Movie {
    public ChildrensMovie(String title) {
        super(title, Movie.CHILDRENS);
    }

    double determineMovieAmount(int daysRented) {
        double thisAmount = 0;
        switch (priceCode) {
            case REGULAR:
                thisAmount += 2;
                if (daysRented > 2)
                    thisAmount += (daysRented - 2) * 1.5;
                break;
            case NEW_RELEASE:
                thisAmount += daysRented * 3;
                break;
            case CHILDRENS:
                thisAmount += 1.5;
                if (daysRented > 3)
                    thisAmount += (daysRented - 3) * 1.5;
                break;
        }
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
