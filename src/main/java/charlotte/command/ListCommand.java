package charlotte.command;

import charlotte.command.Command;
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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showMessage("Your task list is currently empty");
        } else {
            ui.printLine();
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.getSize(); i++) {
                System.out.println((i + 1) + ". " + tasks.getTask(i));
            }
            ui.printLine();
        }

    }

}