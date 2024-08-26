public class DateTimeException extends Exception {    
    public DateTimeException(String name) {
        super(String.format("Please provide datetimes for %s in the yyyy-mm-dd HH:mm format!", name));
    }
}
