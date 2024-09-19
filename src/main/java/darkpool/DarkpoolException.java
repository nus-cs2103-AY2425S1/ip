package darkpool;

/**
 * DarkpoolException is a custom exception class that extends the Exception class.
 * It is used to throw exceptions when there is an error in the Darkpool class.
 */
public class DarkpoolException extends Exception {

    public DarkpoolException(String errorMessage) {
        super(errorMessage);
    }

}
