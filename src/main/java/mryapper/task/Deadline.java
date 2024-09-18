package mryapper.task;

/**
 * A type of task which has a deadline.
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Creates a task with a deadline.
     *
     * @param description The description of the task.
     * @param deadline The deadline of the task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public Task edit(TaskField field, String newString) {
        switch (field) {
        case DESCRIPTION:
            setDescription(newString);
            return this;
        case DEADLINE:
            this.deadline = newString;
            return this;
        default:
            throw new IllegalArgumentException("You can only edit a deadline task with /description"
                    + " or /deadline");
        }
    }


    @Override
    public String getDataString() {
        return String.format("D ||| %d ||| %s ||| %s\n",
                this.getStatus(), this.getDescription(), this.deadline);
    }

    @Override
    public String toString() {
        String deadlineString = String.format(" (by: %s)", deadline);
        return "[D]" + super.toString() + deadlineString;
    }
}
