package shnoop.command;

import shnoop.storage.Storage;
import shnoop.tasks.TaskList;
import shnoop.ui.Ui;

/**
 * Encapsulates all the relevant actions to be taken when a List Command is issued.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.list(tasks.list());
    }
}
