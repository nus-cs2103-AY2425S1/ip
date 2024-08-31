package commands;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        int i = taskList.size();
        ui.showToUser("Here are the tasks in your list:\n");
        for (int j = 1; j < i + 1; j++) {
            Task x = taskList.get(j - 1);
            ui.showToUser(j + ". " + x);
        }
    }
}
