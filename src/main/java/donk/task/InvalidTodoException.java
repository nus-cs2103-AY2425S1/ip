package donk.task;

/**
 * Exception thrown upon receiving invalid input for creating todo
 */
public class InvalidTodoException extends Exception {
    public InvalidTodoException(String msg) {
        super(msg);

    }
}
