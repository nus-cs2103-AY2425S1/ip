package totoro.task;


/**
 * Represents a todo task in the task management applicaton
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task with the specified description
     *
     * @param description The description of the todo task
     *
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
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
