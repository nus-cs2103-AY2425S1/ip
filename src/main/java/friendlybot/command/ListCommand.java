package friendlybot.command;

import friendlybot.Storage;
import friendlybot.Ui;
import friendlybot.task.Task;
import friendlybot.task.TaskList;

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
     *
     * @return Returns a response String from FriendlyBot to the User.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder sb = new StringBuilder();
        int numTasks = tasks.getNumTasks();
        if (numTasks == 0) {
            Ui.print("You do not have any tasks in your list!");
            return "You do not have any tasks in your list!";
        }
        Ui.print("Here are the tasks in your list:");
        sb.append("Here are the tasks in your list:\n");
        for (int i = 1; i <= numTasks; i++) {
            Task task = tasks.getTask(i);
            Ui.print(i + "." + task.toString());
            sb.append(i).append(".").append(task.toString());
            if (i != numTasks) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "list - Displays all tasks.";
    }
}
