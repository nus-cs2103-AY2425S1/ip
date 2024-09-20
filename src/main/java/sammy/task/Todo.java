package sammy.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        assert description != null && !description.isEmpty() : "Description cannot be null or empty";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

