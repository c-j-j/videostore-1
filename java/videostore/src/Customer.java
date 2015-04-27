import java.util.*;

public class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "";
        result += statementTitle();

        for (Rental rental : rentals) {

            double thisAmount = determineAmount(rental);
            frequentRenterPoints += determineFrequentRenterPoints(rental);
            result += generateReportLine(rental, thisAmount);
            totalAmount += thisAmount;
        }

        result += generateFooterLine(totalAmount, frequentRenterPoints);
        return result;
    }

    private String statementTitle() {
        return String.format("Rental Record for %s\n", getName());
    }

    private String generateFooterLine(double totalAmount, int frequentRenterPoints) {
        return String.format("You owed %s\n", totalAmount)
                + String.format("You earned %d frequent renter points\n", frequentRenterPoints);
    }

    private String generateReportLine(Rental rental, double thisAmount) {
        return String.format("\t%s\t%s\n", rental.getMovie().getTitle(), thisAmount);
    }

    private int determineFrequentRenterPoints(Rental rental) {
        int frequentRenterPoints = 1;

        if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE
                && rental.getDaysRented() > 1)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }

    private double determineAmount(Rental rental) {
        double thisAmount = 0;

        // determines the amount for each line
        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (rental.getDaysRented() > 2)
                    thisAmount += (rental.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                thisAmount += rental.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if (rental.getDaysRented() > 3)
                    thisAmount += (rental.getDaysRented() - 3) * 1.5;
                break;
        }
        return thisAmount;
    }


}
