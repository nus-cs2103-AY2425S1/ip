public class DevonInvalidEventException extends DevonException {
    @Override
    public String toString() {
        return super.toString() + " Event is invalid! Usage: event [task] /from [start_time] /to [end_time]";
    }
}
