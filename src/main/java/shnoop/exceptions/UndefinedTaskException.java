package shnoop.exceptions;

/**
 * Obsolete Exception representing a situation where a Task was created without a Task Type.
 */
public class UndefinedTaskException extends Exception {

    public UndefinedTaskException(String message) {
        super(message);
    }

    /**
     * Default constructor method.
     */
    public UndefinedTaskException() {
        super("✿ Shnoop ✿: You could travel the world, but nothing comes close to choosing a task type.\n"
                + "✿ Shnoop ✿: Try typing 'todo', 'event' or 'deadline' followed by stating the task description.");
    }
}
