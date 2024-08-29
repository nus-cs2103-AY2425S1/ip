package ChatterBoxErrors;

public class ChatterBoxToDoError extends ChatterBoxError {
    public ChatterBoxToDoError() {
        super("""
              ____________________________________________________________
              todo Usage: todo {String taskName}
              ____________________________________________________________""");
    }
}
