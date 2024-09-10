package mahesh.command;

public class IncompleteCommand extends Command {
    private final String errMessage;

    public IncompleteCommand(String errMessage) {
        this.errMessage = errMessage;
    }

    @Override
    public String execute() {
        return this.errMessage;
    }
}
