package potong.exceptions;

/**
 * Exception specific to the chatbot.
 */
public class IllegalInputPotongException extends PotongException {

    /**
     * Initialise the exception.
     */
    public IllegalInputPotongException(String message) {
        super(String.format("OOPS!! meow have a wrong input!\n%s", message));
    }
}
