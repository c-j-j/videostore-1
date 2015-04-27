import java.util.*;

public class Statement {
    private String customerName;
    private List<Rental> rentals = new ArrayList<>();

    public Statement(String customerName) {
        this.customerName = customerName;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getCustomerName() {
        return customerName;
    }

    public String generateStatement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String statementText = "";
        statementText += statementTitle();

        for (Rental rental : rentals) {

            double thisAmount = rental.determineAmount();
            frequentRenterPoints += rental.determineFrequentRenterPoints();
            statementText += generateReportLine(rental, thisAmount);
            totalAmount += thisAmount;
        }

        statementText += generateFooterLine(totalAmount, frequentRenterPoints);
        return statementText;
    }

    private String statementTitle() {
        return String.format("Rental Record for %s\n", getCustomerName());
    }

    private String generateFooterLine(double totalAmount, int frequentRenterPoints) {
        return String.format("You owed %s\n", totalAmount)
                + String.format("You earned %d frequent renter points\n", frequentRenterPoints);
    }

    private String generateReportLine(Rental rental, double thisAmount) {
        return String.format("\t%s\t%s\n", rental.getMovie().getTitle(), thisAmount);
    }


}
