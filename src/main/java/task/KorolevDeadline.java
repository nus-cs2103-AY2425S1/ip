package task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a task of deadline type
 */
public class KorolevDeadline extends KorolevTask {
    private LocalDateTime deadline;

    /**
     * Constructs new object of KorolevDeadline.
     *
     * @param name description of the task
     * @param date deadline
     */
    public KorolevDeadline(String name, String date) {
        super(name);
        this.deadline = LocalDateTime.parse(date);
    }

    /**
     * Overrides toString method in KorolevTask.
     *
     * @return string representation of KorolevDeadline
     */
    @Override
    public String toString() {
        String base = super.toString();
        String tag = "D";
        String head = "[" + tag + "]";
        String deadlines;
        deadlines = "by " + this.deadline.format(
            DateTimeFormatter.ofPattern("HH:mm MMM d yyyy").withLocale(Locale.ENGLISH)
        );

        return head + base + " (" + deadlines + ")";
    }
}
