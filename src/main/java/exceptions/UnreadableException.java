package exceptions;

public class UnreadableException extends Throwable {

    public UnreadableException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }
}
