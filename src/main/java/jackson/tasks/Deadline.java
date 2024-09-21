package jackson.tasks;

import jackson.utils.CustomDateTime;

/**
 * Deadline class containing name and deadline.
 */
public class Deadline extends Task {
    private CustomDateTime deadline;

    /**
     * Constructs Deadline Task instance.
     * @param name String name of the task.
     * @param deadline String deadline of the task.
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = new CustomDateTime(deadline);
    }

    @Override
    public CustomDateTime getStartDateTime() {
        return null;
    }

    @Override
    public CustomDateTime getEndDateTime() {
        return this.deadline;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", this.getTaskType(), super.toString(), this.deadline);
    }
}
