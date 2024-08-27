package friendlybot.command;

import friendlybot.Storage;
import friendlybot.Ui;
import friendlybot.task.TaskList;
import friendlybot.task.Task;

/**
 * ListCommand is a Command that prints all the tasks stored in TaskList.
 */
public class ListCommand extends Command {
    /**
     * Prints all the tasks stored in tasks upon execution.
     *
     * @param tasks An instance of TaskList where the new task is added to.
     * @param ui An instance of Ui (User Interface) that handles the interactions between FriendlyBot and user.
     * @param storage An instance of Storage that loads tasks and saves tasks in a file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.print("Here are the tasks in your list:");
        int numTasks = tasks.getNumTasks();
        for (int i = 1; i <= numTasks; i++) {
            Task task = tasks.getTask(i);
            Ui.print(i + "." + task.toString());
        }
    }
}
