package ChatterBoxErrors;

public class ChatterBoxError extends Exception {
    public ChatterBoxError() {
        super("""
              ____________________________________________________________
              ChatterBox does not understand you :(
              ____________________________________________________________""");
    }

    public ChatterBoxError(String message) {
        super(message);
    }
}
