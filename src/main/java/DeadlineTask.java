import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class DeadlineTask extends Task {
    protected String by;

    public DeadlineTask(String description, String by) {
        super(description);
        try {
            LocalDate byDate = LocalDate.parse(by.split(" ")[0]);
            String time = by.split(" ")[1];
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmm");
            LocalTime byTime = LocalTime.parse(time, dtf);
            this.by = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            this.by += byTime.format(DateTimeFormatter.ofPattern(" h:mma"));
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            this.by = by;
        }
    }

    public DeadlineTask(String description, String by, boolean isDone) {
        super(description, isDone);
        try {
            LocalDate byDate = LocalDate.parse(by.split(" ")[1]);
            String time = by.split(" ")[2];
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmm");
            LocalTime byTime = LocalTime.parse(time, dtf);
            this.by = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            this.by += byTime.format(DateTimeFormatter.ofPattern(" h:mma"));
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            this.by = by;
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
