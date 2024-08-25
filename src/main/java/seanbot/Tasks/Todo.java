package seanbot.Tasks;

/**
 * Represents a todo task in the SeanBot application.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the Todo task to a string suitable for saving to a file.
     * @return A string representation of the Todo task for file storage.
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
    
    /**
     * Returns a string representation of the Todo task, including its status.
     * @return A string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
