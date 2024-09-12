package darwin.task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private String symbol = "D";
    private LocalDateTime deadline;

    /**
     * Initialises a Deadline task with name and deadline.
     * @param name A string of the task.Task's name.
     * @param deadline A string indicating the task.Task's deadline.
     */
    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String getTaskInfo() {
        return super.getTaskInfo() + String.format(" (by: %s)", super.formatDate(this.deadline));
    }

    @Override
    public String toCsv() {
        return super.toCsv() + "," + this.deadline;
    }
}
