package spongebob.command;

import spongebob.storage.Storage;
import spongebob.storage.TaskList;
import spongebob.ui.Ui;

/**
 * Command used when no suitable command is found
 */
public class UnknownCommand extends Command {

    /**
     *  Executes the command, showing the unknown command message
     * @param taskList  Tasklist of Spongebob
     * @param ui    Ui containing all UI components
     * @param storage   Storage to keep all entries to a .txt file
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {

        assert !storage.isEmpty() : "storage is empty!";

        return ui.unknownCommand();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String[] getArgs() {
        return new String[] {"unknown"};
    }
}
