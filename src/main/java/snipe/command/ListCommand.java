package snipe.command;

import snipe.exception.SnipeException;
import snipe.storage.Storage;
import snipe.core.TaskList;
import snipe.util.Ui;

/**
 * The {@code ListCommand} class represents a command to display all tasks in the task list.
 * It formats the list of tasks and displays them to the user through the user interface.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by formatting the tasks in the task list and displaying them to the user.
     * The tasks are displayed in a numbered list format.
     *
     * @param tasks   The task list containing all tasks to be displayed.
     * @param ui      The user interface used to display the task list.
     * @param storage The storage object (not used in this command).
     * @throws SnipeException If an application-specific error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnipeException {
        StringBuilder message = new StringBuilder();
        message.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            String item = String.format("%d. %s\n", i + 1, tasks.getTask(i).toString());
            message.append(item);
        }
        String resultMessage = message.toString().stripTrailing();
        ui.printWithLines(resultMessage);
    }
}
