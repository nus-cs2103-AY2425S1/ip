package ipman.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a <code>Task</code> that has a deadline for its completion
 *
 * @see Task
 */
public class Deadline extends Task {
    public static final char TASK_TYPE = 'D';
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final LocalDate by;

    /**
     * Constructs a <code>Deadline</code> that needs to be completed by a
     * certain date.
     *
     * @param name name of the task
     * @param by date this task needs to be completed by
     */
    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    @Override
    public char getTaskType() {
        return TASK_TYPE;
    }

    /**
     * Constructs a <code>Deadline</code> from a previously serialized
     * <code>Deadline</code>.
     *
     * @param serializedDeadline string from previously serialized deadline
     * @return parsed <code>Deadline</code> from the serialized string
     * @see Task#serialize()
     */
    public static Deadline deserialize(String serializedDeadline) {
        String[] values = serializedDeadline.split("\\|");
        Deadline deadline = new Deadline(values[2], LocalDate.parse(values[3]));
        if (values[1].equals("X")) {
            deadline.markDone();
        }
        return deadline;
    }

    @Override
    public String serialize() {
        return String.format(
            "%s|%c|%s|%s",
            this.getTaskType(),
            this.isDone ? 'X' : 'O',
            this.name,
            this.by
        );
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", this.by.format(dateFormat));
    }
}
