package Buu;

/**
 * Represents a to-do task in the GPT application.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toFileFormat() {
        return String.format("T | %d | %s | %d", isDone ? 1 : 0, description, priority);
    }

    @Override
    protected String getTaskType() {
        return "T";
    }
}
