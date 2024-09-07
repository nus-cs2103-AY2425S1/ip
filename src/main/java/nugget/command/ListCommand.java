package nugget.command;

import nugget.Storage;
import nugget.TaskList;
import nugget.Ui;
import nugget.exception.NuggetException;

/**
 * Represents a command that lists all tasks in the task list.
 */
public class ListCommand implements Command {

    /**
     * Executes the list command, displaying all tasks in the task list.
     *
     * @param tasks   The list of tasks to be displayed.
     * @param ui      The user interface that displays messages to the user.
     * @param storage The storage that handles the saving of tasks.
     * @throws NuggetException If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NuggetException {
        int len = tasks.size();
        StringBuilder output = new StringBuilder();
        if (len == 0) {
            output.append("No tasks found!\n"); // Append error message if no tasks are available
        } else {
            output.append("You have the following tasks:\n");
            for (int i = 0; i < len; i++) {
                output.append(String.format("%d.%s\n", i + 1, tasks.getTask(i))); // Append each task to the output
            }
        }

        // Display the concatenated output in the GUI
        ui.showMessage(output.toString());
    }
}
