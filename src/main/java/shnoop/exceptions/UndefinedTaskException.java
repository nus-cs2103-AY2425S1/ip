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
        super("You could travel the world, but nothing comes close to choosing a task type.\n\n"
                + "I have a few California Gorls for you to try out. \n "
                + "Try typing 'todo', 'event' or 'deadline' followed by stating the task description. \n"
                + "Other commands you could try out too are list, mark, unmark, find \n"
                + "And don't tell anyone else I told you this but you can also type 'list 1' to sort tasks!");
    }
}
