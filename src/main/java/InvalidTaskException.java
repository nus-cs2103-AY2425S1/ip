public class InvalidTaskException extends Throwable {
    public InvalidTaskException(int index) {
        super("____________________________________________________________\n"
                + "OOPS!!! Task " + (index + 1) + " does not exist.\n"
                + "____________________________________________________________\n");
    }
}
