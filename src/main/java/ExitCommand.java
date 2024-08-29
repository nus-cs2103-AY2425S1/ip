/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

