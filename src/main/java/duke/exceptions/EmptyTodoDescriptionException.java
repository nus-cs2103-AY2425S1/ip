package duke.exceptions;

public class EmptyTodoDescriptionException extends Exception {
    /**
     * Creates a duke.exceptions.EmptyTodoException when the user enters a todo without specifying the description.
     *
     * @param e: description of the exception
     */
    public EmptyTodoDescriptionException(String e) {
        super(e);
    }
}
