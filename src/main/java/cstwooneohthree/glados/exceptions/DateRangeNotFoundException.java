package cstwooneohthree.glados.exceptions;

/**
 * DateRangeNotFoundException class when dates are not found.
 *
 * @author jayjay19630
 */
public class DateRangeNotFoundException extends GladosException {
    /**
     * Constructs DateRangeNotFoundException by calling the parent class.
     */
    public DateRangeNotFoundException() {
        super("Date range between '/from' and '/to' not specified or invalid");
    }
}
