package cstwooneohthree.glados.exceptions;

/**
 * DateNotFound class for when date does not exist in deadline.
 *
 * @author jayjay19630
 */
public class DateNotFoundException extends GladosException {
    /**
     * Constructs DateNotFoundException by calling the parent class.
     */
    public DateNotFoundException() {
        super("Date after '/by' not specified or invalid");
    }
}
