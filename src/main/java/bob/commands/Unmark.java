package bob.commands;

import bob.data.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

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
        return "OK, I've marked this task as not done yet:\n" + list.get(index);
    }
}
