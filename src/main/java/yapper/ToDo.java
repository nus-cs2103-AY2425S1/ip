package yapper;

/**
 * Represents a ToDo Task.
 */
public class ToDo extends Task {
    /**
     * Creates an instance of ToDo.
     *
     * @param taskName Name of the task.
     */
    public ToDo(String taskName) {
        super(taskName);
        super.setTaskTag("todo");
    }

    /**
     * Returns a string representation of the ToDo object.
     *
     * @return String representation of the ToDo object.
     */
    public String toString() {
        String doneSymbol = super.getIsDone() ? "[X]" : "[ ]";
        return String.format("[T]%s %s", doneSymbol, super.getName());
    }

    /**
     * Returns the string representation of the ToDo object to be recorded into a file.
     * String to be decoded when loading history from the file.
     *
     * @return String representation of the ToDo object.
     */
    public String toFile() {
        String fileDoneSymbol = super.getIsDone() ? "D" : "N";
        return String.format("T %s--%s", fileDoneSymbol, super.getName());
    }
}
