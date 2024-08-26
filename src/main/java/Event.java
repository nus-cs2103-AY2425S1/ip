import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task implements Serializable {
    protected LocalDate from;
    protected LocalDate to;


    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() +
                " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
}
