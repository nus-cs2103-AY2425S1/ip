package duke.exceptions;

public class EmptyTaskDescriptionException extends Exception {
    /**
     * Creates a duke.exceptions.EmptyTodoException when the user enters a task without specifying the description.
     *
     * @param e: description of the exception
     */
    public EmptyTaskDescriptionException(String e) {
        super(e);
    }
}
