public class ExitCommand extends CommandBase {
    public ExitCommand() {
        isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // No action needed for exit command.
    }
}

