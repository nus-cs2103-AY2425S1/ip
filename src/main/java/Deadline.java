/**
 * This class represents a Deadline with a description, completion status and end time.
 *
 * <p>This class inherits from the Task class It maintains a description
 * of the deadline, whether the task has been completed or not and the end time.</p>
 */

public class Deadline extends Task{

    protected String endTime;

    /**
     * Constructs a new instance of Deadline.
     *
     * @param description String description of Deadline.
     * @param endTime String end time of Deadline.
     */
    public Deadline(String description, String endTime) {
        super(description);
        this.endTime = endTime;
    }

    /**
     * Constructs a new instance of Event.
     *
     * @param description String description of Deadline.
     * @param isDone Boolean indicating whether Deadline is done or not.
     * @param endTime String end time of Deadline.
     */
    public Deadline(String description, Boolean isDone, String endTime) {
        super(description, isDone);
        this.endTime = endTime;
    }

    /**
     * Gets end time of the Deadline.
     *
     * @return description endTime which indicates when the Deadline is due by.
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Returns a string representation of the Deadline.
     *
     * <p>The string includes the status icon and the description of the Deadline.</p>
     *
     * @return A string in the format {"[statusIcon] description"}.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endTime + ")";
    }

}
