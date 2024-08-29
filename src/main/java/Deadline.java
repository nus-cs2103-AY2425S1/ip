import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Deadline extends Task{
    private LocalDate deadline;

    public Deadline(String description) throws UnknownTimeException {
        super(description.substring(0, description.indexOf("/by") - 1));
        this.deadline = this.stringToDate(description.substring(description.indexOf("/by") + 4));
    }

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = this.stringToDate(deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateToString(this.deadline) + ")";
    }

    public String fileString() {
        return super.fileString() + " | " + this.deadline;
    }

    private LocalDate stringToDate(String s) {
        return LocalDate.parse(s);
    }

    private String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getDay() {
        return this.deadline.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
    }
}
