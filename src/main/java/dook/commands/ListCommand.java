package dook.commands;

import java.io.IOException;

import dook.DookException;
import dook.storage.Storage;
import dook.tasks.TaskList;
import dook.ui.Ui;

/**
 * The command that lists all the tasks in the TaskList.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DookException, IOException {
        ui.separate();
        String message = "";
        if (tasks.isEmpty()) {
            throw new DookException("No tasks");
        } else {
            for (int i = 0; i < tasks.numOfTasks(); i++) {
                ui.showMessage((i + 1) + ". " + tasks.getTask(i));
                message = message.concat((i + 1) + ". " + tasks.getTask(i) + "\n");
            }
        }
        ui.separate();
        return message;
    }
}
