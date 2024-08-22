public class ChatterBoxDeadlineError extends ChatterBoxError {
    public ChatterBoxDeadlineError() {
        super("____________________________________________________________\n"
                + "deadline Usage: deadline {String taskName} /by {String endTime}\n"
                + "____________________________________________________________");
    }
}
