package boombotroz;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deals with DEADLINE typed task.
 */
public class Deadline extends Task {
    private String time;

    /**
     * Creates Deadline object.
     *
     * @param mark state of completion for task.
     * @param task task description.
     * @param time time the task is due.
     * @param priority priority level of task.
     */
    public Deadline(boolean mark, String task, String time, int priority) {
        super(mark, task, priority);
        this.time = time;

    }

    /**
     * @inheritDoc
     */
    public void hasDate(Ui ui) throws BoomException {
        if (this.time.matches("\\d{4}-\\d{2}-\\d{2}")) {
            // checks if deadline already passed
            LocalDate d1 = LocalDate.parse(this.time);
            LocalDate d2 = LocalDate.now();
            ui.isWrongDeadline(d1, d2);

            this.time = d1.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
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
