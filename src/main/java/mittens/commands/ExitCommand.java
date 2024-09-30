package mittens.commands;

import mittens.MittensException;
import mittens.parser.BadInputException;
import mittens.parser.RawCommandElements;
import mittens.storage.Storage;
import mittens.task.TaskList;
import mittens.ui.Ui;

/**
 * Represents a command for exiting the program.
 */
public class ExitCommand extends Command {
    /**
     * Creates a new ExitCommand object with the specified command elements.
     * It assumes the command name corresponds with this class.
     *
     * @param elements The RawCommandElements object
     */
    public ExitCommand(RawCommandElements elements) {
        assert elements.getCommand().equals("exit") : "Command name should be matching";
        this.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks);

            ui.showGoodbyeMessage();
        } catch (MittensException e) {
            ui.showErrorMessage(e);
        }
    }
}
