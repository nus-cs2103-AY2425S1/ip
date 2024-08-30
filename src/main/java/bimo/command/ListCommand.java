package bimo.command;

import bimo.Storage;
import bimo.TaskList;
import bimo.Ui;

/**
 * Creates a command that list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Lists all task created.
     *
     * @param tasks List of user tasks.
     * @param ui User interface that interacts with users.
     * @param storage Storage that writes and load files.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("    Here are the tasks in your tasks:");
        for (int i = 0; i < tasks.getLength(); i++) {
            String message = String.format("    %d. %s", i + 1, tasks.getTask(i).toString());
            System.out.println(message);
        }
    }

}
