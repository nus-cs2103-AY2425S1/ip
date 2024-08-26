import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static DateTimeFormatter outputDateFormat = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
    private static DateTimeFormatter fileDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private LocalDateTime from;
    private LocalDateTime to;
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format(String.format("E | " + super.toString() + " | " + from.format(outputDateFormat) + "-"
                + to.format(outputDateFormat)));
    }
    @Override
    public String toFileString() {
        return String.format(String.format("E | " + super.toFileString() + " | " + from.format(fileDateFormat) + "-"
                + to.format(fileDateFormat)));
    }
}