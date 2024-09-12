package charlotte.command;

import charlotte.storage.Storage;
import charlotte.task.TaskList;
import charlotte.ui.Ui;

/**
 * Represents a command to display the list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to display the list of tasks.
     * <p>
     * This method checks if the task list is empty. If it is, it displays a message informing the user
     * that the list is empty. If not, it prints a numbered list of all tasks in the task list.
     * </p>
     *
     * @param tasks The TaskList object containing all the tasks to be displayed.
     * @param ui The Ui object used to display messages to the user.
     * @param storage The Storage object, which is not used in this command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        StringBuilder result = new StringBuilder();

        //check if task list is empty
        if (tasks.isEmpty()) {
            result.append("Your task list is currently empty\n");
        } else {
            result.append(ui.printLine()).append("\n");
            result.append("Here are the tasks in your list:\n");

            //Add each task to the result string
            for (int i = 0; i < tasks.getSize(); i++) {
                assert tasks.getTask(i) != null : "Task should not be null";
                result.append((i + 1)).append(". ").append(tasks.getTask(i)).append("\n");
            }
            result.append(ui.printLine()).append("\n");
        }

        return result.toString();
    }
}
