package botty.exceptions;

/**
 * Exception thrown when no tasks are found
 */
public class TasksNotFoundException extends BottyException {
    /**
     * Constructs a {@code TasksNotFoundException} with default message.
     */
    public TasksNotFoundException() {
        super("No tasks were found!");
    }
}
