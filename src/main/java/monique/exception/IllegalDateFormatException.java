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
     * Provides advice on how to handle this exception.
     * This method prints a message informing the user that they have used an incorrect date-time format
     * and advises them to try again with the correct input format.
     */
    @Override
    public void advice() {
        System.out.println("You have tried to create an Event without using proper "
                + "date-time formats. Please try again, with the correct input format");

    }
}
