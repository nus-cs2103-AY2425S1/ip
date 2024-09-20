package fred.Exceptions;

public class InvalidTagException extends FredException {
    public InvalidTagException() {
        super("OOPS!!! Please use this format: tag 1 fun");
    }
}
