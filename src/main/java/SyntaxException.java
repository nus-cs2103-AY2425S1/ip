/**
 * Thrown when invalid input format is used. Contains message denoting which command it failed at.
 */
public class SyntaxException extends JacksonException {
    public SyntaxException(String msg) {
        super(msg);
    }
}
