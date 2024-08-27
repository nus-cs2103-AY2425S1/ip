package exceptions;

public class DateNotParsedException extends GladosException {
    public DateNotParsedException() {
        super("Date is incorrectly formatted (should be yyyy-MM-dd)");
    }
}
