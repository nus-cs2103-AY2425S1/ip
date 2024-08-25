package task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
public class KorolevEvent extends KorolevTask {
    private String duration;
    private String tag;
    private LocalDateTime start;
    private LocalDateTime end;

    public KorolevEvent(String name, String date) {
        super(name);
        this.duration = date;
        this.tag = "E";
    }

    public KorolevEvent(String name, String start, String end) throws DateTimeParseException {
        super(name);
        this.start = LocalDateTime.parse(start);
        this.end = LocalDateTime.parse(end);
        this.tag = "E";
    }

    @Override
    public String toString() {
        String base = super.toString();
        String head = "[" + this.tag + "]";
        String from = "from: " + this.start.format(
                DateTimeFormatter.ofPattern("HH:mm MMM d yyyy").withLocale(Locale.ENGLISH));
        String to = "to: " + this.end.format(
                DateTimeFormatter.ofPattern("HH:mm MMM d yyyy").withLocale(Locale.ENGLISH));;

        return head + base + " (" + from + " " + to + ")";
    }
}
