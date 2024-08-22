public class Deadline extends Task {
    private String symbol = "D";
    private String deadline;

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
