package yoda.exceptions;

public class EmptyInputException extends YodaException {
    public EmptyInputException() {
        super("Hmm... You must speak to be heard...");
    }
}
