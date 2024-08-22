public class ToDoTask extends Task {
    private final String SYMBOL = "T";

    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[%s][$s] %s", this.SYMBOL, super.isDone, super.description);
    }
}
