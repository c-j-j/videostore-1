import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return statementTitle() + rentalLines() + footerLine(calculateTotalAmount(), calculateFrequentRenterPoints());
    }

    private String rentalLines() {
        return rentals.stream().map(this::generateReportLine).collect(Collectors.joining());
    }

    private double calculateTotalAmount() {
        return rentals.stream().mapToDouble(Rental::determineAmount).sum();
    }

    private int calculateFrequentRenterPoints() {
        return rentals.stream().mapToInt(Rental::determineFrequentRenterPoints).sum();
    }

    private String statementTitle() {
        return String.format("Rental Record for %s\n", customerName);
    }

    private String footerLine(double totalAmount, int frequentRenterPoints) {
        return String.format("You owed %s\n", totalAmount)
                + String.format("You earned %d frequent renter points\n", frequentRenterPoints);
    }

    private String generateReportLine(Rental rental) {
        return String.format("\t%s\t%s\n", rental.getTitle(), rental.determineAmount());
    }


}
