package fred.Exceptions;

public class InvalidTagException extends FredException {
    public InvalidTagException() {
        super("OOPS!!! That's not the correct format for a tag");
    }
}
