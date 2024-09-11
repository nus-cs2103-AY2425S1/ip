package boombotroz;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deals with Event typed task.
 */
public class Event extends Task {
    private String timeStart;
    private String timeEnd;

    /**
     * Creates Event object.
     *
     * @param mark state of completion for task.
     * @param task task description.
     * @param timeStart time the task begins.
     * @param timeEnd time the task ends.
     */
    public Event(boolean mark, String task,
                 String timeStart, String timeEnd) {
        super(mark, task);
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;

    }

    /**
     * @inheritDoc
     */
    @Override
    public void hasDate(Ui ui) throws BoomException {
        if (timeStart.matches("\\d{4}-\\d{2}-\\d{2}")
                && timeEnd.matches("\\d{4}-\\d{2}-\\d{2}")) {
            //checks if end already passed or if end earlier than start
            LocalDate d1 = LocalDate.parse(timeStart);
            LocalDate d2 = LocalDate.parse(timeEnd);
            LocalDate d3 = LocalDate.now();
            ui.isWrongEventTime(d1, d2, d3);

            timeStart = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            timeEnd = d2.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        String s;
        s = String.format("[E]%s (from: %s to: %s)",
                super.toString(), this.timeStart, this.timeEnd);
        return s;

    }

}
