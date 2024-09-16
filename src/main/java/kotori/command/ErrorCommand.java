package kotori.command;

/**
 * This class represents an Error command.
 * */
public class ErrorCommand extends Command {
    private String message;
    public ErrorCommand(String message) {
        this.message = message;
    }
    @Override
    public String execute() {
        return message;
    }
}
