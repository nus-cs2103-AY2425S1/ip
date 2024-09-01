package task;

import exceptions.AlreadyCompletedException;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A deadline task.
 *
 * @author IsaacPangTH
 */
public class Deadline extends Task {

    private LocalDate dueDate;

    /**
     * Constructor for a<code>Deadline</code>.
     *
     * @param description Description of the deadline.
     * @param dueDate Due date of the deadline.
     */
    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Factory method which returns a<code>Deadline</code>.
     *
     * @param description Description of the deadline.
     * @param dueDateString Due date of deadline in String form in the format (date /at time).
     * @return Deadline from information provided.
     */
    public static Deadline of(String description, String dueDateString) {
        String[] args = dueDateString.split("/at");
        LocalDate dueDate = LocalDate.parse(args[0].trim());
        if (args.length > 1) {
            return new TimeSpecificDeadline(description, dueDate, LocalTime.parse(args[1].trim()));
        }
        return new Deadline(description, dueDate);
    }

    /**
     * Factory method which returns a<code>Deadline</code>from a String containing data of the deadline.
     *
     * @param data Data of the deadline in form "completion status|description|due date".
     * @return Deadline from data.
     */
    public static Deadline of(String data) {
        String[] args = data.split("\\|");
        Deadline deadline = Deadline.of(args[1], args[2]);
        if (Boolean.parseBoolean(args[0])) {
            try {
                deadline.complete();
            } catch (AlreadyCompletedException e) {
                throw new RuntimeException(e);
            }
        }
        return deadline;
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }

    /**
     * Returns data of the deadline as a string.
     *
     * @return data in form "type|completion status|description|due date"
     */
    @Override
    public String toData() {
        return super.toData() + "|" + dueDate;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + dueDate + ")";
    }
}
