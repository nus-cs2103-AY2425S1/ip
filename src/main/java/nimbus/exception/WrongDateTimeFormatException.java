package nimbus.exception;

/**
 * Exception for the wrong dateTime format for DeadlineTask and EventTask
 */
public class WrongDateTimeFormatException extends NimbusException {

    /**
     * Creates exception with message to show users the format for dateTime
     */
    public WrongDateTimeFormatException() {
        super("Wrong DateTime format, Follow DD/MM/YYYY 24HR Time \n"
                + "Eg: 2/12/2019 1800");
    }
}
