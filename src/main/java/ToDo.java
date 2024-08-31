public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("%s (Task) %s", printStatus(), getDescription());
    }

    public String toSaveFile() {
        return String.format("T:%s:%s", isDone() ? "x" : "", getDescription());
    }

}
