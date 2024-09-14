package shoai;

import shoai.Task; // Imports the Task class since Todo extends Task

/**
 * Represents a task of type "Todo". This class extends the Task class
 * to represent a task that does not have any specific deadline or time range.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return A string representation of the Todo task, formatted as "[T][done status] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
