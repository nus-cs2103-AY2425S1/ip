package astor.exception;

public class EmptyInputException extends AstorException {
    public EmptyInputException() {
        super("Please type something!");
    }
}
