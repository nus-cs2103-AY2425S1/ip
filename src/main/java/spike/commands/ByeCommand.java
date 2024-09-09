package spike.commands;

import spike.storage.Storage;
import spike.storage.TaskList;
import spike.ui.Ui;

/**
 * Represents a command to exit the program.
 */
public class ByeCommand extends Command {

    /**
     * @inheritDoc
     */
    @Override
    public String getCommandType() {
        return "Bye";
    }

    /**
     * Executes the command to exit the program.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface of the program.
     * @param storage The storage of the program.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    /**
     * Returns whether the command is an exit command.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
