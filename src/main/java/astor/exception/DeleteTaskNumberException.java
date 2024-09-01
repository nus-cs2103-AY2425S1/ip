package astor.exception;

/**
 * Represents an exception where the user did not specify a task index for deleting tasks.
 *
 * @author Choi Yi Hao
 */
public class DeleteTaskNumberException extends AstorException {
    public DeleteTaskNumberException() {
        super("Specify task index to delete!");
    }
}
