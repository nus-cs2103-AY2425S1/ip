package ponderpika.exception;

/**
 * Exception thrown when an invalid date and time is encountered.
 * <p>
 * This exception extends the PonderPikaException class and indicates that the
 * date and time provided by the user are not valid.
 * </p>
 */
public class InvalidDateTimeException extends PonderPikaException {

    /**
     * Returns a string representation of the exception which includes
     * the default message from the superclass along with a specific message stating that
     * user has entered an invalid date and time to the string representation
     *
     * @return A string that represents the detailed exception
     */
    @Override
    public String toString() {
        return (super.toString() + "You have entered invalid date and time!\n" + "Format should be: DD/MM/YYYY HH:mm");
    }
}
