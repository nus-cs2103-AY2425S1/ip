public class DateNotFoundException extends GladosException {
    public DateNotFoundException() {
        super("GLaDOS: Date after '/by' not specified or invalid");
    }
}
