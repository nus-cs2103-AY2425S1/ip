package jackson.tasks;

import jackson.utils.Temporal;

/**
 * Deadline class containing name and deadline.
 */
public class Deadline extends Task {
    private Temporal deadline;

    /**
     * Constructor for Deadline Task class.
     * @param name String name of the task
     * @param deadline String deadline of the task
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = new Temporal(deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
