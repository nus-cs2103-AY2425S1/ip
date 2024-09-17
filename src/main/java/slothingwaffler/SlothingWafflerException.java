package slothingwaffler;

/**
 * Exception class for handling errors in the Slothing Waffler application.
 * <p>
 * This class is used to signal application-specific exceptions.
 * </p>
 */
public class SlothingWafflerException extends Exception {

    /**
     * Constructs a SlothingWafflerException with the specified detail message.
     *
     * @param msg the detail message
     */
    public SlothingWafflerException(String msg) {
        super(msg);
    }

}
