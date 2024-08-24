public class Deadline extends Task {
    private String symbol = "D";
    private String deadline;

    /**
     * Initialises a Deadline Task with name and deadline.
     * @param name A string of the Task's name.
     * @param deadline A string indicating the Task's deadline.
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
}
