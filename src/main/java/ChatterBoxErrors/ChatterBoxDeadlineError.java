package ChatterBoxErrors;

public class ChatterBoxDeadlineError extends ChatterBoxError {
    public ChatterBoxDeadlineError() {
        super("""
              ____________________________________________________________
              deadline Usage: deadline {String taskName} /by {String endTime}
              ____________________________________________________________""");
    }
}
