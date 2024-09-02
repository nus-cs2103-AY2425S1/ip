package monique.exception;

/**
 * The <code>IllegalDateFormatException</code> class represents an exception that is thrown
 * when an invalid date format is encountered. It extends from <code>MoniqueException</code>.
 */
public class IllegalDateFormatException extends MoniqueException {
    /**
     * Constructs a new <code>IllegalDateFormatException</code> with a default detail message.
     * The message indicates that the date format provided is illegal.
     */
    public IllegalDateFormatException() {
        super("Illegal Date Format");
    }

    /**
     * Provides advice on how to handle an illegal date format exception.
     *
     * @return a string containing advice for handling the exception, indicating that the date-time format used is incorrect and providing guidance to use the correct format.
     */
    @Override
    public String advice() {
        return "You have tried to create an Event without using proper "
                + "date-time formats. Please try again, with the correct input format";

    }
}
