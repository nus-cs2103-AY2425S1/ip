import java.io.Serializable;

public class Deadline extends Task implements Serializable {
    private String deadline;

    /**
     * Creates task that need to be done before a specific date/time.
     *
     * @param task task information of the deadline task.
     * @param deadline time when task need to be done.
     */
    public Deadline(String task, String deadline) {
        this.task = task;
        this.deadline = deadline;
    }

    /**
     * Returns the information of a deadline task.
     *
     * @return information the task in "[D][-] Task (by: 'deadline')" format.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

}
