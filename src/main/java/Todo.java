public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String convertToSavedString() {
        return "T | " + super.convertToSavedString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
