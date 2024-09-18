package wenjigglybot;

/**
 * Custom exception class for handling incorrect deadline input in the wenjigglybot system.
 * This exception is thrown when the deadline format provided by the user is invalid.
 * <p>
 * Example of a correct deadline input: "deadline (name) /by (date)".
 */
public class DeadlineException extends Exception {

    /**
     * Overrides the default toString method to provide a custom error message.
     *
     * @return A string explaining the correct deadline format to the user.
     */
    @Override
    public String toString() {
        return "You have provided an incorrect deadline input!!! Correct format is deadline (name) /by (date)";
    }

}