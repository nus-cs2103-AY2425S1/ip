package atlas.exceptions;

public class AtlasException extends Exception {
    /**
     * @param message Error message that will be displayed to the user.
     */
    public AtlasException(String message) {
        super("OOPS!!! " + message);
    }
}