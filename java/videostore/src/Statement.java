import java.util.*;
import java.util.function.ToIntFunction;

public class Statement {
    private final String customerName;
    private final List<Rental> rentals = new ArrayList<>();

    public Statement(String customerName) {
        this.customerName = customerName;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String generateStatement() {
        double totalAmount = 0;
        String statementText = "";
        statementText += statementTitle();

        for (Rental rental : rentals) {
            double thisAmount = rental.determineAmount();
            statementText += generateReportLine(rental, thisAmount);
            totalAmount += thisAmount;
        }

        statementText += generateFooterLine(totalAmount, determineFrequentRenterPoints());
        return statementText;
    }

    private int determineFrequentRenterPoints() {
        return rentals.stream().mapToInt(Rental::determineFrequentRenterPoints).sum();
    }

    private String statementTitle() {
        return String.format("Rental Record for %s\n", customerName);
    }

    private String generateFooterLine(double totalAmount, int frequentRenterPoints) {
        return String.format("You owed %s\n", totalAmount)
                + String.format("You earned %d frequent renter points\n", frequentRenterPoints);
    }

    private String generateReportLine(Rental rental, double thisAmount) {
        return String.format("\t%s\t%s\n", rental.getTitle(), thisAmount);
    }


}
