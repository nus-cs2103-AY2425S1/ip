public class DeadlineTask extends Task {
    private final String SYMBOL = "D";
    private String dueTime;

    public DeadlineTask(String description, String dueTime) {
        super(description);
        this.dueTime = dueTime;
    }

    @Override
    public String toString() {
        return String.format("[%s][$s] %s (by: %s)", this.SYMBOL, super.isDone, super.description, this.dueTime);
    }
}
