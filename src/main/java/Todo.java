public class Todo extends Task {
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", type.getSymbol(), status.getSymbol(), description);
    }
}