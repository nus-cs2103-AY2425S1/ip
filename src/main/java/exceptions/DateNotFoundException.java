package exceptions;

public class DateNotFoundException extends GladosException {
    public DateNotFoundException() {
        super("Date after '/by' not specified or invalid");
    }
}
