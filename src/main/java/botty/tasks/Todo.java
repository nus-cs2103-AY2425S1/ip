package botty.tasks;

import botty.exceptions.BottyException;
import botty.exceptions.CorruptedTaskStringException;
import botty.exceptions.EmptyArgumentException;

/**
 * A Todo
 */
public class Todo extends Task {
    /**
     * Constructs a {@code Todo} with the given completed and description
     * @param completed whether the task is completed
     * @param description the description
     */
    public Todo(boolean completed, String description) throws EmptyArgumentException {
        super(completed, description);
        if (description.isEmpty()) {
            throw new EmptyArgumentException("description");
        }
    }
    /**
     * Constructs a {@code Todo} with the given description, set as not completed
     * @param description the description
     */
    public Todo(String description) throws EmptyArgumentException {
        this(false, description);
    }

    /**
     * Returns a string representation of the {@code Todo}
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    /**
     * Constructs a {@code Todo} from a data string for loading from file
     * @param taskString the data string from file
     * @return the constructed task
     * @throws BottyException if corrupted task string or invalid arguments
     */
    public static Todo fromDataString(String taskString) throws BottyException {
        if (!taskString.matches("T \\| [10] \\| (.*?)")) {
            throw new CorruptedTaskStringException();
        }

        String[] arguments = taskString.split(" \\| ");

        boolean completed = arguments[1].equals("1");
        String description = arguments[2];

        return new Todo(completed, description);
    }
    /**
     * Returns a string representation of the {@code Todo} that is used for local storage
     */
    @Override
    public String toDataString() {
        return "T | " + getCompletedAndDescription();
    }
}
