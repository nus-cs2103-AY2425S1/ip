package bob.commands;

import bob.storage.Storage;
import bob.data.TaskList;
import bob.ui.Ui;

public class List extends Command {
    private static void listTasks(TaskList list) {
        if (list.isEmpty()) {
            System.out.println("There are no tasks in your list.");
            return;
        }

        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        listTasks(list);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
