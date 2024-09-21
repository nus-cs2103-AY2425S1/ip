package shnoop.command;

import shnoop.storage.Storage;
import shnoop.tasks.TaskList;
import shnoop.ui.Ui;

/**
 * Encapsulates all the relevant actions to be taken when a List Command is issued.
 */
public class ListCommand extends Command {
    int mode;

    public ListCommand(int mode) {
        this.mode = mode;
    }

    public ListCommand() {
        mode = 0;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.list(tasks.sortedList(mode));
    }
}
