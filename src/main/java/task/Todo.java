package task;

import parser.Parser;

/**
 * Represents a task.Todo task.
 *
 * @author jordanchan
 */
public class Todo extends Task {
    /**
     * Constructs a task.Todo task with a given description.
     *
     * @param description The description of the task.Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    public void editTask(String newInfo, Parser p) {
        //do nothing because todo tasks have no time frame associated with them
    }

    /**
     * Returns a string representation of the task.Todo task.
     *
     * @return The string representation of the task.Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.name;
    }
}
