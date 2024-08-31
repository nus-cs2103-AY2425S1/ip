import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate due;

    public Deadline(String description, String due) throws GrokInvalidUserInputException {
        super(description);
        try {
            this.due = LocalDate.parse(due);
        } catch (DateTimeParseException e) {
            throw new GrokInvalidUserInputException("Invalid date format. Please declare your date in the ISO format yyyy-mm-dd.");
        }
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + "(by: " + due.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
