import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class EventTask extends Task {
    protected String from;
    protected String to;

    public EventTask(String description, String from, String to) {
        super(description);
        try {
            LocalDate fromDate = LocalDate.parse(from.split(" ")[0]);
            String time = from.split(" ")[1];
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmm");
            LocalTime fromTime = LocalTime.parse(time, dtf);
            this.from = fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            this.from += fromTime.format(DateTimeFormatter.ofPattern(" h:mma"));
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            this.from = from;
        }
        try {
            LocalDate toDate = LocalDate.parse(to.split(" ")[0]);
            String time = to.split(" ")[1];
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmm");
            LocalTime toTime = LocalTime.parse(time, dtf);
            this.to = toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            this.to += toTime.format(DateTimeFormatter.ofPattern(" h:mma"));
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            this.to = to;
        }
    }

    public EventTask(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        try {
            LocalDate fromDate = LocalDate.parse(from.split(" ")[0]);
            String time = from.split(" ")[1];
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmm");
            LocalTime fromTime = LocalTime.parse(time, dtf);
            this.from = fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            this.from += fromTime.format(DateTimeFormatter.ofPattern(" h:mma"));
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            this.from = from;
        }
        try {
            LocalDate toDate = LocalDate.parse(to.split(" ")[0]);
            String time = to.split(" ")[1];
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmm");
            LocalTime toTime = LocalTime.parse(time, dtf);
            this.to = toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            this.to += toTime.format(DateTimeFormatter.ofPattern(" h:mma"));
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            this.to = to;
        }
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
