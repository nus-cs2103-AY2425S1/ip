import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class DeadlineTask extends Task {
    protected String by;

    public DeadlineTask(String description, String by) {
        super(description);
        String timeBy = "";
        try {
            LocalDate byDate = LocalDate.parse(by.split(" ")[0]);
            this.by = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            this.by = by;
        }
        try {
            timeBy = by.split(" ")[1];
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmm");
            LocalTime byTime = LocalTime.parse(timeBy, dtf);
            this.by += byTime.format(DateTimeFormatter.ofPattern(" h:mma"));
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            this.by += " " + timeBy;
        }
    }

    public DeadlineTask(String description, String by, boolean isDone) {
        super(description, isDone);
        String timeBy = "";
        try {
            LocalDate byDate = LocalDate.parse(by.split(" ")[0]);
            this.by = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            this.by = by;
        }
        try {
            timeBy = by.split(" ")[1];
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmm");
            LocalTime byTime = LocalTime.parse(timeBy, dtf);
            this.by += byTime.format(DateTimeFormatter.ofPattern(" h:mma"));
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            this.by += " " + timeBy;
        }
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
