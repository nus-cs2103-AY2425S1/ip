package exceptions;

/**
 * An exception that occurs when a deadline description has no date
 */
public class DeadlineNoDateException extends TestamentException {

    /**
     * Constructor for DeadlineNoDateException
     */
    public DeadlineNoDateException() {
        super("You have not provided the date for this deadline.\nThe format for deadlines is as follows:\n"
                + "deadline (details) /by (date)");
    }

}
