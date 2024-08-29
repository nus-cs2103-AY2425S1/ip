package ChatterBoxErrors;

public class ChatterBoxInvalidDateTimeError extends ChatterBoxError {
    public ChatterBoxInvalidDateTimeError() {
        super("""
              ____________________________________________________________
              Please input date time in the format of dd/mm/yyyy HHmm (24 hour format)
              ____________________________________________________________""");
    }
}
