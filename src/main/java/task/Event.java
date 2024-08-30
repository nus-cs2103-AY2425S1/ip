package task;
import exception.InputFormatException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    private final LocalDateTime fromDate;
    private final LocalDateTime toDate;

    public Event(String description, LocalDateTime fromDate, LocalDateTime toDate) throws InputFormatException{
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String toFileFormatString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("E | %s | %s | %s", super.toFileFormatString(), fromDate.format(formatter), toDate.format(formatter));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:ma");
        return String.format("[E] %s (from: %s to: %s)\n",super.toString(), fromDate.format(formatter), toDate.format(formatter));
    }



    public static boolean matchEvent(String s) {
        return s.startsWith("event");
    }
}
