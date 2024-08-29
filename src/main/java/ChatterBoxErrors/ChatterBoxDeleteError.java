package ChatterBoxErrors;

public class ChatterBoxDeleteError extends ChatterBoxError {
    public ChatterBoxDeleteError() {
        super("""
              ____________________________________________________________
              delete Usage: delete {Integer taskNo}
              ____________________________________________________________""");
    }
}
