package fred.Exceptions;

public class InvalidTaskNumberException extends FredException {
    public InvalidTaskNumberException() {
        super("OOPS!!! That's not a valid task number!");
    }
}
