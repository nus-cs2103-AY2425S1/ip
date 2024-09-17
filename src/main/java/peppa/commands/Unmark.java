package peppa.commands;

import peppa.data.TaskList;
import peppa.storage.Storage;
import peppa.ui.Ui;

/**
 * Represents the unmark command.
 */
public class Unmark extends Command {
    private final int index;
    public Unmark(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        return unmarkTask(list);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String unmarkTask(TaskList list) {
        list.get(index).markAsNotDone();
        return "Ok! I've marked this task as not done yet:\n" + list.get(index);
    }
}
