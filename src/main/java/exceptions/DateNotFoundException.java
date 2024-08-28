package exceptions;

public class DateNotFoundException extends GladosException {
    /**
     * Constructs DateNotFoundException by calling the parent class.
     */
    public DateNotFoundException() {
        super("Date after '/by' not specified or invalid");
    }
}
