package yapbot.tasks;

/**
 * Child class of Task that has an additional deadline field.
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Creates a Deadline instance with isDone set to false by default.
     *
     * @param description Details of the Task.
     * @param deadline Date/time when this task should be completed by.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Creates a Deadline instance and allows isDone to be set to any boolean value.
     *
     * @param description Details of the Task.
     * @param deadline Date/time when this task should be completed by.
     * @param isDone Set to true for task to be completed by default.
     */
    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String saveTask() {
        return "D/" + super.saveTask() + "/" + this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

}
