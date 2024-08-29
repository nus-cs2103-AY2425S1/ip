package CancelGPT.exception.command;

public class UnknownInput extends Exception {
    public UnknownInput() {
        super("Unknown input. Please enter an appropriate command that is known.");
    }
}
