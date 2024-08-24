public class EmptyException extends Throwable {
    public EmptyException(String command) {
        super("____________________________________________________________\n"
                + "OOPS!!! The description of a " + command + " cannot be empty.\n"
                + "____________________________________________________________\n");
    }
}
