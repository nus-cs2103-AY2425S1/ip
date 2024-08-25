package task;

public class Deadline extends Task {
    private String symbol = "D";
    private String deadline;

    /**
     * Initialises a task.Deadline task.Task with name and deadline.
     * @param name A string of the task.Task's name.
     * @param deadline A string indicating the task.Task's deadline.
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }
    @Override
    public String getSymbol() {
        return "D";
    }

    @Override
    public String getTaskInfo() {
        return super.getTaskInfo() + String.format(" (by: %s)", this.deadline);
    }
    @Override
    public String toCsv() {
        return super.toCsv() + "," + this.deadline;
    }
}
