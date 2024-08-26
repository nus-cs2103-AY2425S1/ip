package task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class KorolevDeadline extends KorolevTask {
    private LocalDateTime deadline;

    /**
     *
     * @param name
     * @param date
     */
    public KorolevDeadline(String name, String date) {
        super(name);
        this.deadline = LocalDateTime.parse(date);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String base = super.toString();
        String tag = "D";
        String head = "[" + tag +"]";
        String deadlines;
        deadlines = "by " + this.deadline.format(
            DateTimeFormatter.ofPattern("HH:mm MMM d yyyy").withLocale(Locale.ENGLISH)
        );

        return head + base + " (" + deadlines + ")";
    }
}
