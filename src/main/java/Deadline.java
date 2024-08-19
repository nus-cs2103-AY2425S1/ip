public class Deadline extends Task {
    /**
     * The deadline of the task.
     */
    private String deadline;

    /**
     * Constructor for a new deadline task.
     *
     * @param name the name of the deadline task
     *
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Get the string representation of the deadline task.
     * @return the string representation of the deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
