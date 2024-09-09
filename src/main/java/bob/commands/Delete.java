package bob.commands;

import bob.data.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

/**
 * Class representing the delete command.
 */
public class Delete extends Command {
    private final int index;

    public Delete(int index) {
        this.index = index;
    }

    private String deleteTask(TaskList list) {
        String response = "Noted. I've removed this task:\n" + list.get(index);
        list.remove(index);
        String response2 = "Now you have " + list.size() + (list.size() == 1 ? " task in the list."
                : " tasks in the list.");
        return response + "\n" + response2;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        return deleteTask(list);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
