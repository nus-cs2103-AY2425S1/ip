package barcus.command;

import barcus.storage.Storage;
import barcus.tasklist.TaskList;

/**
 * Command to list out all tasks
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage) {
        if (tasks.getLength() == 0) {
            output = "No tasks stored yet, use todo, deadline or event to add a task!";
        } else {
            output = "Okie, here are your tasks!\n" + tasks.getTaskListDisplay();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
