public class InvalidCommand extends Command {

    private String message;

    public InvalidCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute() {
        Ui.printError(message);
    }

    @Override
    public boolean isTerminated() {
        return false;
    }
}
