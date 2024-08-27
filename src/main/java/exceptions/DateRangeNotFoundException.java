package exceptions;

public class DateRangeNotFoundException extends GladosException {
    public DateRangeNotFoundException() {
        super("Date range between '/from' and '/to' not specified or invalid");
    }
}
