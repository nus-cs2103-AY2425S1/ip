package momo;

public class EmptyCommandException extends MomoException {
    public EmptyCommandException() {
        super("I dare you to leave an empty command again...");
    }

}
