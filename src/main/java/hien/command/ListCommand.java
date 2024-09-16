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
    public void execute(TaskList tasks, UI ui, Storage storage) throws HienException {
        this.listTasks(tasks, ui);
    }

    private void listTasks(TaskList tasks, UI ui) throws HienException {
        if (tasks.isEmpty()) {
            ui.showMessage(" There are no tasks in your list.");
        } else {
            ui.showMessage(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                String taskMessage = " " + (i + 1) + "." + tasks.getTask(i);
                ui.showMessage(taskMessage);
            }
        }
    }

}
