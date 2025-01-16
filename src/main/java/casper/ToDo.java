package casper;

/**
 * Represents the ToDo class
 */
public class ToDo extends Task {
    public ToDo(String description, boolean isDone) {
        super(description, "T", isDone);
    }
}
