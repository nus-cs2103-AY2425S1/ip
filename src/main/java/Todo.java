public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toLoad() {
        return "[T]" + super.toString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

