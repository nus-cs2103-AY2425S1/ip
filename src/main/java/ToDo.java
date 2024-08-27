public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public ToDo() {
        super();
    }

    @Override
    public String toString() {
        if (getIsDone()) {
            return String.format("[X] | To-Do | %s", getDescription());
        } else {
            return String.format("[ ] | To-Do | %s", getDescription());
        }
    }
}
