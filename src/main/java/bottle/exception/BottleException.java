package bottle.exception;

/**
 * The type Bottle exception.
 */
public class BottleException extends RuntimeException {
    /**
     * Instantiates a new Bottle exception.
     *
     * @param errorMsg the error msg
     */
    public BottleException(String errorMsg) {
        super(errorMsg);
    }
}
