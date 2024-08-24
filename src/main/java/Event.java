import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public Event(String description, String startDate, String endDate) {
        super(description, 'E', startDate, endDate);
    }

    public String getDescription() {
        String formatStartDate = super.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formatEndDate = super.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return super.description + " (From: " + formatStartDate + " To: " + formatEndDate + ")";
    }
}
