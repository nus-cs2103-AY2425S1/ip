package Boombotroz;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deals with DEADLINE typed task.
 */
public class Deadline extends Task {
    String time;

    public Deadline(boolean mark, String task, String time) {
        super(mark, task);
        this.time = time;

    }

    /**
     * @inheritDoc
     */
    public void hasDate(Ui ui) throws BoomException {
        if (this.time.matches("\\d{4}-\\d{2}-\\d{2}")) {
            LocalDate d1 = LocalDate.parse(this.time);
            LocalDate d2 = LocalDate.now();
            ui.wrongDeadline(d1, d2);
            this.time = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }

    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        String s;
        s = String.format("[D]%s (by: %s)",
                super.toString(), this.time);
        return s;

    }

}
