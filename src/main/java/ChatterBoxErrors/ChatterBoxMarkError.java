package ChatterBoxErrors;

public class ChatterBoxMarkError extends ChatterBoxError {
    public ChatterBoxMarkError() {
        super("""
              ____________________________________________________________
              mark/ unmark Usage: mark/ unmark {Integer taskNo}
              ____________________________________________________________""");
    }
}
