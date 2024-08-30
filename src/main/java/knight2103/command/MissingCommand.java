package knight2103.command;

public class MissingCommand extends Exception {
    private MissingCommand(String message) {
        super(message);
    }

    public MissingCommand() {
        this("parsing of command failed");
    }
}
