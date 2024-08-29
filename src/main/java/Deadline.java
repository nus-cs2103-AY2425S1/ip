/**
 * Represents a task that needs to be done before a specific date/time.
 * A Deadline object corresponds to a task with a deadline
 * in the Chobo chatbot.
 */
public class Deadline extends Task {
    private String date;
    /**
     * Creates a new Deadline task.
     *
     * @param name The description of the task.
     * @param done The status of the task (true if done, false otherwise).
     * @param date The deadline of the task.
     */
    public Deadline(String name, boolean done, String date) {
        super(name, done);
        this.date = date;
    }

    @Override
    public String toFileString() {
        return String.format("%s|%d|%s|%s", this.getType(), this.getIsDone() ? 1 : 0, this.getName(), date);
    }

    @Override
    public String getType() {
        return "T";
    }

    /**
     * Returns a string representation of the Deadline task, including its type,
     * status, name, and deadline.
     *
     * @return A string representing the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
