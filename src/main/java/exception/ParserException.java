package exception;

public class ParserException extends ToMoException {
    public ParserException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "ParserException: " + getMessage();
    }
}
