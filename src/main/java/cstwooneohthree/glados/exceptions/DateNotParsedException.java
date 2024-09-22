package cstwooneohthree.glados.exceptions;

/**
 * DateNotParsedExcetion class when date cannot be parsed.
 *
 * @author jayjay19630
 */
public class DateNotParsedException extends GladosException {
    /**
     * Constructs DateNotParsedException by calling the parent class.
     */
    public DateNotParsedException() {
        super("Date is incorrectly formatted (should be yyyy-MM-dd)");
    }
}
