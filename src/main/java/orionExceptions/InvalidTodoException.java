package orionExceptions;

public class InvalidTodoException extends  OrionException{
    public InvalidTodoException(String message) {
        super("You put: " + message + " . Please use todo [task] to use the todo command");
    }
}
