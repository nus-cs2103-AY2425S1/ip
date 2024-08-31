package exceptions;

public class EmptyDeadlineException extends AstorException {
    public EmptyDeadlineException() {
        super("Please input your deadline!");
    }
}
