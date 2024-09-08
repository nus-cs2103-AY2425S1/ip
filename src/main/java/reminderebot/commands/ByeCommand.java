package reminderebot.commands;

import reminderebot.ReminderebotException;
import reminderebot.Storage;
import reminderebot.TaskList;
import reminderebot.Ui;


/**
 * The ByeCommand class represents a command to exit Reminderebot.
 */
public class ByeCommand extends Command {
    /**
     * Instantiate a ByeCommand
     */
    public ByeCommand() {}

    /**
     * Saves data and exits Reminderebot
     * @param tasklist
     * @param ui
     * @param storage
     * @return String representing exit command
     * @throws ReminderebotException
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        storage.saveData(tasklist);
        return ui.goodbye();
    }

    /**
     * Bye exits Reminderebot.
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
