package bob.commands;

import bob.storage.Storage;
import bob.data.TaskList;
import bob.ui.Ui;

public class Delete extends Command {
    private final int index;

    public Delete(int index) {
        this.index = index;
    }

    private void deleteTask(TaskList list) {
        System.out.println("Noted. I've removed this task:\n" + list.get(index));
        list.remove(index);
        System.out.println("Now you have " + list.size() + (list.isEmpty() ? " task in the list." : " tasks in the list."));
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        deleteTask(list);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
