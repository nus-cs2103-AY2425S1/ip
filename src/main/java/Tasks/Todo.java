package Tasks;

import Exceptions.DelphiException;
import Exceptions.EmptyInputException;

/**
 * Represents a Tasks.Todo task.
 *
 * @author jordanchan
 */
public class Todo extends Task {
    /**
     * Constructs a Tasks.Todo task with a given description.
     *
     * @param description The description of the Tasks.Todo task.
     * @throws EmptyInputException if the description is empty.
     */
    public Todo(String description) throws DelphiException {
        super(description);
    }

    /**
     * Returns a string representation of the Tasks.Todo task.
     *
     * @return The string representation of the Tasks.Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.name;
    }
}
