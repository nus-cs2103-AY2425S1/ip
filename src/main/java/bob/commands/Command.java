package bob.commands;

import bob.data.TaskList;
import bob.storage.Storage;
import bob.tasks.Task;
import bob.ui.Ui;

/**
 * Represents the command interface.
 */
public abstract class Command {

    public abstract String execute(TaskList task, Ui ui, Storage storage);

    public abstract boolean isExit();

    public static String addTask(TaskList list, Task t) {
        String addTaskResponse = "Got it. I've added this task:\n" + t;
        String taskCounterResponse ="Now you have " + list.size() + (list.size() == 1 ? " task in the list."
                : " tasks in the list.");
        return addTaskResponse + "\n" + taskCounterResponse;
    }
}
