package yapbot.tasks;

/**
 * Child class of Task that has an additional deadline field.
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Returns a Deadline instance.
     *
     * @param description Details of the Task.
     * @param deadline Date/time when this task should be completed by.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

}
