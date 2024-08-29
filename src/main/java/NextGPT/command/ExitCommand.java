package NextGPT.command;
import NextGPT.TaskList;
import NextGPT.Ui;
import NextGPT.Storage;

/**
 * Subclass of Command that exits user.
 */
public class ExitCommand extends Command{
    /**
     * Notifies user with exit message.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
    }

    public boolean isExit() {
        return true;
    }
}
