package reminderebot.commands;

import reminderebot.ReminderebotException;
import reminderebot.Storage;
import reminderebot.TaskList;
import reminderebot.Ui;

/**
 * The HelpCommand class represents a command to list all commands for Reminderebot.
 */
public class HelpCommand extends Command {
    /**
     * Instantiate a Help Command.
     */
    public HelpCommand() {}

    /**
     * Executes the Help command
     * @param tasklist
     * @param ui
     * @param storage
     * @return String representing help for all commands
     * @throws ReminderebotException
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) throws ReminderebotException {
        return ui.getHelpText();
    }

    /**
     * Help command does not exit
     * @return False
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
