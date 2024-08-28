public class ChatterBoxInvalidDateTimeError extends ChatterBoxError {
    public ChatterBoxInvalidDateTimeError() {
        super("____________________________________________________________\n"
                + "Please input date time in the format of dd/mm/yyyy HHmm (24 hour format)\n"
                + "____________________________________________________________");
    }

    public ChatterBoxInvalidDateTimeError(String message) {
        super(message);
    }
}
