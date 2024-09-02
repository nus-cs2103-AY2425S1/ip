package devon;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String dbReadableFormat() {
        return String.format("Todo|%d|%s", this.isDone ? 1 : 0, this.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
