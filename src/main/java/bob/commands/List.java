package bob.commands;

import bob.data.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

/**
 * Class representing the list command.
 */
public class List extends Command {

    private static String listTasks(TaskList list) {
        if (list.isEmpty()) {
            return "There are no tasks in your list.";
        }

        String response = "Here are the tasks in your list:";

        StringBuilder tasklistResponse = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            tasklistResponse.append((i + 1) + ". " + list.get(i) + "\n");
        }

        return response + "\n" + tasklistResponse.toString().trim();
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        return listTasks(list);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
