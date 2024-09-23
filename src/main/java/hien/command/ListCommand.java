package hien.command;

import hien.exception.HienException;
import hien.main.Storage;
import hien.main.TaskList;
import hien.ui.UI;

public class ListCommand extends Command {
    private String command;
    private UI ui;
    public ListCommand(boolean isExit) {
        super(isExit);
    }
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws HienException {
        return this.listTasks(tasks, ui);
    }

    private String listTasks(TaskList tasks, UI ui) throws HienException {
        if (tasks.isEmpty()) {
            return ui.showMessage(" There are no tasks in your list.");
        } else {
            String msg = ui.showMessage(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                String taskMessage = " " + (i + 1) + "." + tasks.getTask(i);
                msg += ui.showMessage(taskMessage);
            }
            return msg;
        }
    }

}
