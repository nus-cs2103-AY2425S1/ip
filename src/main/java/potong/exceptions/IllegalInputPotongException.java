package potong.exceptions;

/**
 * Exception specific to the chatbot.
 */
public class IllegalInputPotongException extends PotongException {

    /**
     * Initialise the exception.
     */
    public IllegalInputPotongException() {
        super("OOPS!! Your input is wrong :-(");
    }

    /**
     * String representation of the exception.
     *
     * @return String representation of the exception.
     */
    @Override
    public String toString() {
        return "OOPS!! Your input is wrong :-(";
    }
}
