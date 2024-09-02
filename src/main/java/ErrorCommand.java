public class ErrorCommand extends Command {
    private final String message;

    public ErrorCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExceptionMessage(message);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
