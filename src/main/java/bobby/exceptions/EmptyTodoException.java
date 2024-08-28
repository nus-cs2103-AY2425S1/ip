package bobby.exceptions;

public class EmptyTodoException extends BobbyException {
    public EmptyTodoException() {
        super("The description of todo cannot be empty!");
    }
}
