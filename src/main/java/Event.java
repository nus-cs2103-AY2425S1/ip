import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task{

    protected LocalDateTime dateStart;
    protected LocalDateTime dateEnd;

    public Event(String description, String dateStart, String dateEnd) {
        super(description);
        this.dateStart = LocalDateTime.parse(dateStart.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));;
        this.dateEnd = LocalDateTime.parse(dateEnd.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));;
    }

    public Event(String description, LocalDateTime dateStart, LocalDateTime dateEnd) {
        super(description);
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public LocalDateTime getStartDate() {
        return this.dateStart;
    }

    public LocalDateTime getEndDate() {
        return this.dateEnd;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                dateStart.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")) +
                " to: " + dateEnd.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "Event | " + (isDone ? "Done" : "Not Done") + " | " +
                description + " | " +
                dateStart.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")) +
                " | " +
                dateEnd.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
    }
}
