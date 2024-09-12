package sunny;

import java.time.LocalDate;

/**
 * Represent tasks with deadlines
 */
public class DeadlineTask extends Task {
    /**
     * Initialises a Deadline Task object
     * @param description description of the task
     * @param isDone if the event is done
     */
    public DeadlineTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Initialises a Deadline task object and set the isDone to false
     * @param description description for the task
     */
    public DeadlineTask(String description) {
        super(description);
    }
    String m1 = super.description.split("/by ", 2)[0];
    String deadline = super.description.split("/by ", 2)[1];
    LocalDate d = LocalDate.parse(deadline.trim());

    @Override
    public String getTimeline() {
        return deadline;
    }
    @Override
    public String toString() {
        if (isDone) {
            return String.format("[D][X] %s (by: %s)", m1, deadline);
        } else {
            return String.format("[D][] %s (by: %s)", m1, deadline);
        }
    }
}
