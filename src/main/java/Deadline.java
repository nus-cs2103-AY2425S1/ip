/**
 * This class represents a Deadline with a description, completion status and end time.
 *
 * <p>This class inherits from the Task class It maintains a description
 * of the deadline, whether the task has been completed or not and the end time.</p>
 */

public class Deadline extends Task{

    protected String endTime;

    /**
     * Constructs a new instance of Event.
     *
     * @param description String description of Event.
     * @param endTime String end time of Event.
     */
    public Deadline(String description, String endTime) {
        super(description);
        this.endTime = endTime;
    }

    /**
     * Returns a string representation of the task.
     *
     * <p>The string includes the status icon and the description of the task.</p>
     *
     * @return A string in the format {"[statusIcon] description"}.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endTime + ")";
    }
}
