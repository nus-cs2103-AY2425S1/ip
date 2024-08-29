/**
 * Represents a Task with the addition of a <code>deadlineTime</code>
 * that it should be finished by.
 */
public class Deadline extends Task {
    private String deadlineTime;


    /**
     * Create a <code>Deadline</code> object.
     * @param deadlineDesc <code>String</code> describing the deadline task.
     * @param deadlineTime <code>String</code> describing the time/date of the deadline.
     */
    Deadline(String deadlineDesc, String deadlineTime) {
        super(deadlineDesc);
        this.deadlineTime = deadlineTime;
    }

    /**
     * Generates a string ready for printing as a description of a <code>Deadline</code>.
     * This includes the <code>Deadline</code> description and deadline time.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + this.deadlineTime + ")";
    }
}
