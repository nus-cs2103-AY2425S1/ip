package twilight;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(boolean isDone, String description) {
        super(description, isDone);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String toStorage() {
        return "T," + super.toStorage();
    }
}
