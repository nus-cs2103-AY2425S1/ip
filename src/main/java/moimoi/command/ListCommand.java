package moimoi.command;

import moimoi.Storage;
import moimoi.TaskList;
import moimoi.Ui;

/**
 * Represents a command to list all existing tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructs a command to list all existing tasks.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Lists all existing tasks.
     *
     * @param storage MoiMoi's storage.
     * @param tasks List of existing tasks.
     * @param ui MoiMoi's user interface.
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.showList(tasks);
    }

}
