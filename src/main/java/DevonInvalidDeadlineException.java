public class DevonInvalidDeadlineException extends DevonException {
    @Override
    public String toString() {
        return super.toString() + " Deadline is invalid! Usage: deadline [task] /by [deadline]";
    }
}
