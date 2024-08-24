/**
 * Thrown when unrecognised command and input is given.
 */
public class UnsupportedException extends JacksonException {
    public UnsupportedException(String msg) {
       super(msg);
    }
}
