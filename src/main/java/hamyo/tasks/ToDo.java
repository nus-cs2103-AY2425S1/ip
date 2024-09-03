package hamyo.tasks;

/**
 * Represents a Task that has no Deadline or Event.
 *
 * @author Han Yu
 */
public class ToDo extends Task {

    /**
     * Constructor for To-Do instance.
     *
     * @param input Derived from user command input. Description for the task,
     *              [Description].
     */
    public ToDo(String... input) {
        super(input);
    }

    /**
     * Converts the To-Do representation to a standardised format for the
     * printing of To-Do.
     *
     * @return Formatted String to represent the To-Do.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    /**
     * Converts the To-Do representation to a standardised format for the loading
     * and storing of tasks in files.
     *
     * @return Formatted String to represent the To-Do.
     */

    @Override
    public String toFileFormat() {
        return "T" + " | " + super.toFileFormat();
    }
}
