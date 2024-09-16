package fred.Exceptions;

public class EmptyTagException extends FredException {
    public EmptyTagException() {
        super("OOPS!!! Looks like you're missing a tag.");
    }
}
