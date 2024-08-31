/**
 * Represents a Task with the addition of a <code>deadlineTime</code>
 * that it should be finished by
 */
public class Deadline extends Task {
    private String deadlineTime;

    /**
     * Create a <code>Deadline</code> object
     * @param deadlineDesc
     * @param deadlineTime
     */
    Deadline(String deadlineDesc, String deadlineTime) {
        this(deadlineDesc, deadlineTime, false);
    }

    Deadline(String deadlineDesc, String deadlineTime, boolean isDone) {
        super(deadlineDesc, isDone);
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

    @Override
    public String getType() {
        return "Deadline";
    }

    @Override
    public String toTsv() {
        return getType() + "\t" + super.toTsv() + "\t" + this.deadlineTime;
    }
}
