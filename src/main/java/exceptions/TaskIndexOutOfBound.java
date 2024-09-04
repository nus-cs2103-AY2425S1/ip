package exceptions;

/**
 * The TaskIndexOutOfBound class is a custom exception that is thrown when the user
 * attempts to refer to a task with an invalid index (i.e., the task does not exist).
 */
public class TaskIndexOutOfBound extends Exception {

    /**
     * Constructs a new TaskIndexOutOfBound with a detailed message indicating that the
     * specified task index is out of bounds.
     */
    public TaskIndexOutOfBound() {
        super(String.format("OOPS!! I'm sorry, but the task you are referring to doesn't exist. %n"
                            + "Check the index again"));
    }
}

