package exceptions;

public class InvalidDeleteFormatException extends HimException {
    public InvalidDeleteFormatException() {
        super("Tell me which task you want me to delete!!!!");
    }
}
