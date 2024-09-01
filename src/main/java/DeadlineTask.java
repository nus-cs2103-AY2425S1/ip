/**
 * This is task with a deadline
 */
public class DeadlineTask extends Task{
    private String byDateTime;

    /**
     * Constructor for DeadlineTask
     *
     * @param description description of task
     * @param byDateTime  deadline of task
     */
    public DeadlineTask(String description, String byDateTime) {
        super(description);
        byDateTime = byDateTime.trim();
        this.byDateTime = byDateTime;
    }

    /**
     * Constructor for DeadlineTask
     *
     * @param description description of task
     * @param byDateTime  deadline of task
     * @param isDone status of task
     */
    public DeadlineTask(String description, String byDateTime, boolean isDone) {
        this(description, byDateTime);
        this.isDone = isDone;
    }

    /**
     * Overrides string representation to show more complete information of Deadline task
     *
     * @return string representation of Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDateTime + ")";
    }
}
