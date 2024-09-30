package neko.task;

/**
 * The Todo class represents a specific type of task in the application.
 * It only contains a name field, which is inherited from the Task class.
 * This class extends the Task class while adding it's own string representation,
 * a "[T]" prefix to the Task string output.
 *
 *
 * @author  Siow Rui Ming
 * @version 0.1
 * @since   2024-08-19
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo object with the specified name.
     * @param name the name or description of the to-do task
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns a string representation of the Todo task.
     * The string is prefixed with "[T]" to indicate it is a to-do task.
     *
     * @return a string representation of the Todo task
     */

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
