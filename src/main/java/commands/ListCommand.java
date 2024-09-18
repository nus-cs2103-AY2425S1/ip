package commands;

import java.util.ArrayList;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command to display the list of the tasks.
 */
public class ListCommand extends Command {

    /**
     * Checks whether this command is an exit command.
     * This command is not an exit command, hence it will return false.
     *
     * @return false, as this command does not signify that the application should terminate.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the list command by displaying the list of tasks.
     *
     * @param tasks The current list of tasks. This parameter allows the command
     *              to retrieve the task list.
     * @param ui The user interface component. This parameter allows the command
     *           to interact with the user interface and print the list.
     * @param storage The storage component. This parameter allows the command
     *                to save or retrieve data from storage. This parameter is
     *                not necessary here.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder output = new StringBuilder();
        output.append("Fists of fury! Here's your list!\n");
        ArrayList<Task> list = tasks.getTasks();
        int len = list.size();
        for (int i = 0; i < len; i++) {
            output.append(i + 1).append(".").append(list.get(i)).append("\n");
        }
        return output.toString();
    }
}
