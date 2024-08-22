public class ChatterBoxError extends Exception {
    public ChatterBoxError() {
        super("____________________________________________________________\n"
                + "ChatterBox does not understand you :(\n"
                + "____________________________________________________________");
    }

    public ChatterBoxError(String message) {
        super(message);
    }
}
