package barney.data.task;

import java.util.ArrayList;


/**
 * Represents a todo task. Inherits from the Task class.
 */
public class TodoTask extends Task {

    /**
     * Constructs a new TodoTask object with the given description.
     *
     * @param description The description of the todo task.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Converts the todo task to an ArrayList of strings for saving purposes.
     *
     * @return An ArrayList of strings representing the todo task.
     */
    @Override
    public ArrayList<String> toSaveArray() {
        ArrayList<String> rtr = super.toSaveArray();
        rtr.add("T");
        return rtr;
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
