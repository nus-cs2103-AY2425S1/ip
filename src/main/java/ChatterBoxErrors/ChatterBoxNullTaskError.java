package ChatterBoxErrors;

public class ChatterBoxNullTaskError extends ChatterBoxError {
    public ChatterBoxNullTaskError() {
        super("""
              ____________________________________________________________
              There is no such task in the list.
              Use the list command to check available task.
              ____________________________________________________________""");
    }
}
