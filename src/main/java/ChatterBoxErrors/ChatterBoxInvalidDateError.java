package ChatterBoxErrors;

public class ChatterBoxInvalidDateError extends ChatterBoxError {
    public ChatterBoxInvalidDateError() {
        super("""
              ____________________________________________________________
              Please input date in the format of dd/mm/yyyy
              ____________________________________________________________""");
    }
}
