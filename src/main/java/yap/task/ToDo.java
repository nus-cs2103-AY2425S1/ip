package yap.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Converts the details of the file into the format represented in the storage file.
     *
     * @return A string in the appropriate format, T | Completion Status (0 or 1) | Description
     */
    @Override
    public String convertToFileString() {
        return "T | " + super.convertToFileString() + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
