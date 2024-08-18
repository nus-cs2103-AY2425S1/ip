public class DateRangeNotFoundException extends GladosException {
    public DateRangeNotFoundException() {
        super("GLaDOS: Date range between '/from' and '/to' not specified or invalid");
    }
}
