import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    public Deadline(String description, String endDate) {
        super(description, 'D', endDate, endDate);
    }

    public String getDescription() {
        String formatEndDate = super.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return super.description + " (by: " + formatEndDate + " " + super.endTime + ")";
    }
}
