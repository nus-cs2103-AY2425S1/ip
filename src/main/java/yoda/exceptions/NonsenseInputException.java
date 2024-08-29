package yoda.exceptions;

public class NonsenseInputException extends InvalidInputException {
    public NonsenseInputException() {
        super("What is the meaning of this...?");
    }
}
