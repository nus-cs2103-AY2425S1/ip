public class ChatterBoxInvalidDateError extends ChatterBoxError {
    public ChatterBoxInvalidDateError() {
        super("____________________________________________________________\n"
                + "Please input date in the format of dd/mm/yyyy\n"
                + "____________________________________________________________");
    }

    public ChatterBoxInvalidDateError(String message) {
        super(message);
    }
}
