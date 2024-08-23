public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", type.getSymbol(), status.getSymbol(), description, by);
    }
}