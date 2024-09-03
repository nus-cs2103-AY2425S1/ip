package bob.commands;

import bob.data.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

/**
 * Represents the mark command.
 */
public class Mark extends Command {
    private final int index;

    public Mark(int index) {
        this.index = index;
    }

    private String markTask(TaskList list) {
        list.get(this.index).markAsDone();
        return "Nice! I've marked this task as done:\n" + list.get(index);
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        return markTask(list);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
