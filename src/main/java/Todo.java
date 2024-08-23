public class Todo extends Task {

    Todo(String description) {
        super(description);
    }

    private Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public Todo markAsDone() {
        return new Todo(getDescription(), true);
    }

    @Override
    public Todo markAsNotDone() {
        return new Todo(getDescription(), false);
    }

    public String getType() {
        return "[T]";
    }

    @Override
    public String toString() {
        return getType() + super.toString();
    }
}