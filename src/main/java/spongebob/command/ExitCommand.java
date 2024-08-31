package spongebob.command;

import spongebob.ui.Ui;
import spongebob.storage.Storage;
import spongebob.storage.TaskList;

/**
 * Command used when exiting the bot
 */

public class ExitCommand extends Command {

    /**
     *  Executes the command, showing the goodbye message
     * @param taskList  Tasklist of Spongebob
     * @param ui    Ui containing all UI components
     * @param storage   Storage to keep all entries to a .txt file
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String[] getArgs() {
        return new String[]{"exit"};
    }


}
