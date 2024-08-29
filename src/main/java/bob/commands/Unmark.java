package bob.commands;

import bob.storage.Storage;
import bob.data.TaskList;
import bob.ui.Ui;

public class Unmark extends Command {
    private final int index;
    public Unmark(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        unmarkTask(list);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private void unmarkTask(TaskList list) {
        list.get(index).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:\n" + list.get(index));
    }
}
