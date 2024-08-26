import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalDateTime dueDate;

    public Deadline(String name, boolean done, String date) throws TarsException {
        super(name, done);
        try {
            this.dueDate = DateTimeParser.parse(date);
        } catch (DateTimeParseException e) {
            throw new TarsException("Invalid date format. Please use the format: yyyy-MM-dd HHmm.");
        }
    }

    public String getDate() {
        return DateTimeParser.format(this.dueDate);
    }

    public void changeDate(String newDate) throws TarsException {
        try {
            this.dueDate = DateTimeParser.parse(newDate);
        } catch (DateTimeParseException e) {
            throw new TarsException("Invalid date format. Please use the format: yyyy-MM-dd HHmm.");
        }
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + getDate() + ")";
    }
}
