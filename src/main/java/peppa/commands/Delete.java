package peppa.commands;

import peppa.data.TaskList;
import peppa.storage.Storage;
import peppa.ui.Ui;

/**
 * Class representing the delete command.
 */
public class Delete extends Command {
    private final int index;

    public Delete(int index) {
        this.index = index;
    }

    private String deleteTask(TaskList list) {
        String removeTaskResponse = "Oh goody! I've removed this task:\n" + list.get(index);
        list.remove(index);
        String taskCounterResponse = "Now you have " + list.size() + (list.isEmpty() ? " task in the list."
                : " tasks in the list.");
        return removeTaskResponse + "\n" + taskCounterResponse;
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
