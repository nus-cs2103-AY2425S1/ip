package task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a task of deadline type.
 */
public class KorolevDeadline extends KorolevTask {
    private LocalDateTime deadline;
    private String type;
    /**
     * Constructs new object of KorolevDeadline.
     *
     * @param name description of the task.
     * @param date deadline.
     */
    public KorolevDeadline(String name, String date) {
        super(name);
        assert !date.isEmpty();
        this.deadline = LocalDateTime.parse(date);
        this.type = "D";
    }

    /**
     * Overrides toString method in KorolevTask.
     *
     * @return string representation of KorolevDeadline.
     */
    @Override
    public String toString() {
        String base = super.toString();
        String head = "[" + type + "]";
        String deadlines;
        deadlines = "by " + this.deadline.format(
            DateTimeFormatter.ofPattern("HH:mm MMM d yyyy").withLocale(Locale.ENGLISH)
        );

        return head + base + " (" + deadlines + ")  " + super.showTag();
    }
}
