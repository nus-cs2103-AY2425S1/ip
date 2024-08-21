public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String toSaveString() {
        return "T" + Barney.SAVE_FILE_DELIMITER + super.toSaveString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
