package task;

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
     * Constructor for a <code>Deadline</code>.
     *
     * @param description Description of the deadline.
     * @param dueDate Due date of the deadline.
     */
    public Deadline(String description, LocalDate dueDate, boolean isCompleted) {
        super(description, isCompleted);
        this.dueDate = dueDate;
    }

    /**
     * Constructor for an incomplete <code>Deadline</code>.
     *
     * @param description Description of the deadline.
     * @param dueDate Due date of the deadline.
     */
    public Deadline(String description, LocalDate dueDate) {
        this(description, dueDate, false);
    }

    /**
     * Factory method which returns a <code>Deadline</code>.
     *
     * @param description Description of the deadline.
     * @param dueDateString Due date of deadline in String form in the format (date /at time).
     * @param isCompleted Completion status of the deadline.
     * @return Deadline from information provided.
     */
    public static Deadline of(String description, String dueDateString, boolean isCompleted) {
        String[] args = dueDateString.split("/at");
        LocalDate dueDate = LocalDate.parse(args[0].trim());

        if (hasTime(dueDateString)) {
            LocalTime dueTime = LocalTime.parse(args[1].trim());
            return new TimeSpecificDeadline(description, dueDate, dueTime, isCompleted);
        }

        return new Deadline(description, dueDate, isCompleted);
    }

    /**
     * Factory method which returns an incomplete <code>Deadline</code>.
     *
     * @param description Description of the deadline.
     * @param dueDateString Due date of deadline in String form in the format (date /at time).
     * @return Deadline from information provided.
     */
    public static Deadline of(String description, String dueDateString) {
        return Deadline.of(description, dueDateString, false);
    }

    /**
     * Factory method which returns a <code>Deadline</code> from a String containing data of the deadline.
     *
     * @param data Data of the deadline in form "completion status|description|due date".
     * @return Deadline from data.
     */
    public static Deadline of(String data) {
        String[] args = data.split("\\|");

        boolean isCompleted = Boolean.parseBoolean(args[0].trim());
        String description = args[1].trim();
        String dueDateString = args[2].trim();

        return Deadline.of(description, dueDateString, isCompleted);
    }

    private static boolean hasTime(String dateString) {
        String[] args = dateString.split("\\|");
        return args.length > 1;
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
        assert this.dueDate != null : "Due Date should not be null";
        return super.toData() + "|" + dueDate;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + dueDate + ")";
    }
}
