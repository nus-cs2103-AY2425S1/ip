package task;

import babygronk.CustomDate;

/**
 * Task child class with deadline delimited by /by
 */
public class Deadline extends Task {
    private static final String TASK_TYPE = "[D]";
    private final CustomDate deadline;

    /**
     * Constructor method for Deadline Task.
     *
     * @param task The task name.
     * @param deadline The deadline of the task.
     */
    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = new CustomDate(deadline);
    }

    @Override
    public String toStorageFormat() {
        return (TASK_TYPE + super.toString() + " (by: " + deadline.unparseDate() + ")");
    }

    @Override
    public String toString() {
        return (TASK_TYPE + super.toString() + " (by: " + deadline + ")");
    }
}
