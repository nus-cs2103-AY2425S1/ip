public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getSaveTaskString() {
        return "T" + super.getSaveTaskString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
