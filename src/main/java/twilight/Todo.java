package twilight;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(boolean status, String description) {
        super(description, status);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String toStorage() {
        return "T," + super.toStorage();
    }
}
