package popi.exception;

public class UnknownCommandException extends PopiException {
    public UnknownCommandException() {
        super("☹ OOPS!!! Please provide a valid command!");
    }
}
