import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private final LocalDate by;

    public DeadlineTask(String description, boolean isDone, String by) throws InvalidDateException {
        super(description, isDone);

        try {
            this.by = Parser.parseDate(by);
        } catch (Exception e) {
            throw new InvalidDateException("Invalid date format. Please use the format dd/MM/yyyy");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.dateToString(by) + ")";
    }

    @Override
    public String simpleFormat() {
        return "D | " + super.simpleFormat() + " | " + by;
    }
}
