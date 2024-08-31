package garfield.commands;

import garfield.storage.Storage;
import garfield.tasks.TaskList;
import garfield.ui.Ui;

/**
 * The ListCommand class represents a command to display the current list of tasks
 * in the Garfield chatbot application.
 */
public class ListCommand extends Command {

    /**
     * Constructs a new ListCommand instance.
     */
    public ListCommand() {
    }

    /**
     * Executes the ListCommand by displaying the current tasks in the task list
     * to the user. If the task list is empty, a message indicating that the list is empty
     * is shown. Otherwise, a summary of the tasks is provided.
     *
     * @param taskList The TaskList to be displayed.
     * @param ui The Ui object used to interact with the user.
     * @param storage The Storage object (not used in this method).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.isEmpty()) {
            ui.showMessage("Your list is empty. Just like my lasagna pan. "
                    + "Are we done here, or are you going to add something?");
        } else {
            String listSummary = "Ugh, here's what you've got so far:\n\n" + taskList.list() +
                    "\nCan we be done now?";
            ui.showMessage(listSummary);
        }
    }
}
