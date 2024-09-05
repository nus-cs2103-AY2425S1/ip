package slave;

/**
 * This exception is thrown when a new event is created with a start date after the end date
 */
public class InvalidChronologicalOrderException extends Exception {
    public InvalidChronologicalOrderException(String s) {
        super(s);
    }
}
