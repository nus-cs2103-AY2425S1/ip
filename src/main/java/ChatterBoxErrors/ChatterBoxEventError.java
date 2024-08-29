package ChatterBoxErrors;

public class ChatterBoxEventError extends ChatterBoxError {
    public ChatterBoxEventError() {
        super("""
              ____________________________________________________________
              event Usage: event {String taskName} /from {String startTime} /to {String endTime}
              ____________________________________________________________""");
    }
}
