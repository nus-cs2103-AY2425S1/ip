package bot.tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toData() {
        // TODO: Replace `T` with enum
        return "T | " + super.toData();
    }
}