package yoda.exceptions;

public class EmptyInputException extends InvalidInputException {
    public EmptyInputException() {
        super("Hmm... You must speak to be heard...");
    }
}
