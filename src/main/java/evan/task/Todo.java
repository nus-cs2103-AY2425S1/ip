package evan.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String encodeAsString() {
        return String.format("T | %s | %s", (this.isDone ? "1" : "0"), description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}