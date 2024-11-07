package chatbuddy.task;

/**
 * Represents a to-do task in the ChatBuddy task list.
 * A ToDo contains only a description.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo with the specified description.
     *
     * @param description The description of the to-do.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        return "T" + " | " + (isDone ? "1" : "0") + " | " + description;
    }
}
