package task;
import prince.Prince;



import task.Task;

/**
 * Represents a regular todo task .
 *
 * A todo task is a type of task that includes an action
 * The class provides multiple methods to retrieve and save that information.
 */

public class ToDoTask extends Task {

    /**
     * Constructs a ToDoTask with the specified description
     * @param description
     */
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the task in a human-readable format.
     *
     * @return String
     */
    @Override
    public String printTask() {
        return "[T]" + super.printTask();
    }

    /**
     * Returns a string representation of the task in a file-storage format.
     *
     * @return String
     */
    @Override
    public String printFileFormat() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }
}
