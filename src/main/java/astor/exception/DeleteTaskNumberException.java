package astor.exception;

public class DeleteTaskNumberException extends AstorException {
    public DeleteTaskNumberException() {
        super("Specify astor.task index to delete!");
    }
}
