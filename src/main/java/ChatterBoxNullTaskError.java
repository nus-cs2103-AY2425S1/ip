public class ChatterBoxNullTaskError extends ChatterBoxError {
    public ChatterBoxNullTaskError() {
        super("____________________________________________________________\n"
                + "There is no such task in the list.\n"
                + "Use the list command to check available task.\n"
                + "____________________________________________________________");
    }
}
