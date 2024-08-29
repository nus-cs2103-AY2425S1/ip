package commands;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.util.ArrayList;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("\nEl Primo:");
        System.out.println("Here are the tasks in your list:");
        ArrayList<Task> list = tasks.getTasks();
        int len = list.size();
        for (int i = 0; i < len; i++) {
            String output = i + 1 + "." + list.get(i);
            System.out.println(output);
        }
    }
}
