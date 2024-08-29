public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getDatabaseString() {
        return String.format("T | %d | %s", this.isDone ? 1 : 0,this.description);
    }
}
