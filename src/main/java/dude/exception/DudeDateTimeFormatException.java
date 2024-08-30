package dude.exception;

/**
 * Thrown to indicate that date and time is provided with incorrect format in the input.
 */
public class DudeDateTimeFormatException extends DudeException {

    /**
     * Constructs a DudeDateTimeFormatException with no detail message.
     */
    public DudeDateTimeFormatException(){
        super("Please input the date and time with the following format: \"yyyy-MM-dd HH:mm\", and with valid value.");
    }
}
