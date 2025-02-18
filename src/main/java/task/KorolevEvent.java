package task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Represents a task of event type.
 */
public class KorolevEvent extends KorolevTask {
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs new object of KorolevEvent.
     *
     * @param name description of the task.
     * @param start starting time.
     * @param end ending time.
     */
    public KorolevEvent(String name, String start, String end) throws DateTimeParseException {
        super(name);
        assert !start.isEmpty();
        assert !end.isEmpty();

        this.start = LocalDateTime.parse(start);
        this.end = LocalDateTime.parse(end);
        this.type = "E";
    }

    /**
     * Overrides toString method in KorolevTask.
     *
     * @return string representation of KorolevEvent.
     */
    @Override
    public String toString() {
        String base = super.toString();
        String head = "[" + this.type + "]";
        String from = "from: " + this.start.format(
                DateTimeFormatter.ofPattern("HH:mm MMM d yyyy").withLocale(Locale.ENGLISH));
        String to = "to: " + this.end.format(
                DateTimeFormatter.ofPattern("HH:mm MMM d yyyy").withLocale(Locale.ENGLISH));;

        return head + base + " (" + from + " " + to + ")  " + super.showTag();
    }
}
