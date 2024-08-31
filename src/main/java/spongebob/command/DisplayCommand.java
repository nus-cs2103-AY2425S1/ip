package spongebob.command;

import spongebob.storage.Storage;
import spongebob.storage.TaskList;
import spongebob.ui.Ui;

/**
 * Command to display, used to show the list
 */
public class DisplayCommand extends Command {

    /**
     * Executes command, displaying the tasklist in the command line
     * @param taskList  Tasklist of Spongebob
     * @param ui    Ui containing all UI components
     * @param storage   Storage to keep all entries to a .txt file
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showList(taskList.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String[] getArgs() {
        return new String[] {"display"};
    }
}
