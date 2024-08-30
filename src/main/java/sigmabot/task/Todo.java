public class Todo extends Task {

    public Todo(String name, String description) {
        super(name, description);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
