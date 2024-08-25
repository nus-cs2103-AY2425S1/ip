import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Deadline extends Task {
    private LocalDateTime dateTime;
    public Deadline(String description, String date) throws DateTimeParseException{
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.dateTime = LocalDateTime.parse(date, formatter);
    }

    public String getTaskType() {
        return "[D]";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return this.getTaskType() + super.toString() + "(by: " + this.dateTime.format(formatter) + ")";    }
}
