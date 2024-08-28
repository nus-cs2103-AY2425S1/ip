package cs2103w10.glados.exceptions;

public class DateRangeNotFoundException extends GladosException {
     /**
     * Constructs DateRangeNotFoundException by calling the parent class.
     */
    public DateRangeNotFoundException() {
        super("Date range between '/from' and '/to' not specified or invalid");
    }
}
