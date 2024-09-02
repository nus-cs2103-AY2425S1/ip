public class DevonInvalidDateTimeException extends DevonException {
    @Override
    public String toString() {
        return super.toString() + " Invalid date-time format for '/from' or '/to'. Please use 'yyyy-MM-dd HHmm'.";
    }
}

