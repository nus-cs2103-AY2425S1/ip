package nave;

/**
 * {@code WrongInputException} is a custom exception class used to signal that
 * an invalid input was encountered. It extends the {@code Exception} class
 * to provide specific error handling for cases where user input does not meet
 * expected criteria.
 */
public class WrongInputException extends Exception {

    /**
     * Constructs a new {@code WrongInputException} with the specified detail
     * message.
     *
     * @param errorMessage the detail message that describes the reason for
     *                     the exception
     */
    public WrongInputException(String errorMessage) {
        super(errorMessage);
    }
}
