public class Todo extends Task {
    public Todo(String description) throws EmptyArgumentException {
        super(description);
        if (description.isEmpty()) {
            throw new EmptyArgumentException("description");
        }
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
