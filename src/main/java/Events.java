import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task{

    private LocalDateTime start, end;

    Events(String content, String start, String end) {
        super(content);
        this.start = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.end = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));;
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (from: "
                + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"))
                + " to: "
                + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"))
                + ")";
    }
}
