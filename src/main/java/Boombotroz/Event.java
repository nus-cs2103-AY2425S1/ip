package Boombotroz;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deals with Event typed task.
 */
public class Event extends Task {
    String time_start;
    String time_end;

    public Event(boolean mark, String task,
                 String time_start, String time_end) {
        super(mark, task);
        this.time_start = time_start;
        this.time_end = time_end;

    }

    /**
     * @inheritDoc
     */
    @Override
    public void hasDate(Ui ui) throws BoomException {
        if (time_start.matches("\\d{4}-\\d{2}-\\d{2}")
                && time_end.matches("\\d{4}-\\d{2}-\\d{2}")) {
            LocalDate d1 = LocalDate.parse(time_start);
            LocalDate d2 = LocalDate.parse(time_end);
            LocalDate d3 = LocalDate.now();
            ui.wrongEventTime(d1, d2, d3);
            time_start = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            time_end = d2.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        String s;
        s = String.format("[E]%s (from: %s to: %s)",
                super.toString(), this.time_start, this.time_end);
        return s;

    }

}
