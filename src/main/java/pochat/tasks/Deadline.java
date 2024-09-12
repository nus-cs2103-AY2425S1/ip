package pochat.tasks;

import java.time.LocalDateTime;

/**
 * This class represents a Task object that has both a task description
 *     and a deadline of when it must be done by
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructor that takes in the necessary parameters to form the Deadline object
     * @param taskDescription description of the task
     * @param deadline when the task is due in dd/mm/yyyy HHMM format
     * @param isDone whether the task is done or not
     */
    public Deadline(String taskDescription, String deadline, boolean isDone) {
        super(taskDescription, isDone);
        this.deadline = toLocalDateTime(deadline);
    }

    /**
     * Takes in <code>taskDescription</code> as String, <code>deadline</code>
     *     as String and sets <code>isDone</code> boolean parameter as false
     */
    public Deadline(String taskDescription, String deadline) {
        this(taskDescription, deadline, false);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Deadline) {
            return ((Deadline) o).deadline.equals(this.deadline)
                    && ((Deadline) o).taskDescription.equals(taskDescription);
        }

        return false;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatYearMonthDay(this.deadline) + ")";
    }
}
