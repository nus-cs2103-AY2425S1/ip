package tasks;

public class TodoTask extends Task {
    private static final String SYMBOL = "T";

    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.SYMBOL, super.getStatusIcon(), super.description);
    }
}
