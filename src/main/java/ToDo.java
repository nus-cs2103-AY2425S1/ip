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
            return String.format("[T][X]: %s", getDescription());
        } else {
            return String.format("[T][ ]: %s", getDescription());
        }
    }
}
