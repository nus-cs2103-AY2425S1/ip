package darkpool;

/**
 * Custom exception class for Darkpool application.
 */
public class DarkpoolException extends Exception {

    /**
     * Constructs a new DarkpoolException with the specified detail message.
     *
     * @param errorMessage the detail message.
     */
    public DarkpoolException(String errorMessage) {
        super(errorMessage);
    }

}
