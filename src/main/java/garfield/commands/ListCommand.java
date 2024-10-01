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
     * @param tasks The TaskList to be displayed.
     * @param ui The Ui object used to interact with the user.
     * @param storage The Storage object (not used in this method).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showMessage("Your list is empty. Just like my lasagna pan. "
                    + "Are we done here, or are you going to add something?");
        } else {
            String listSummary = "Ugh, here's what you've got so far:\n\n" + tasks.list()
                    + "\nCan we be done now?";
            ui.showMessage(listSummary);
        }
    }

    /**
     * Executes the ListCommand by displaying the current tasks in the task list
     * to the user. If the task list is empty, returns a String indicating that the list is empty
     * is shown. Otherwise, returns a String representing a summary of the tasks.
     *
     * @param tasks The TaskList to be displayed.
     * @param storage The Storage object (not used in this method).
     * @return A String showing a list of the tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (tasks.isEmpty()) {
            return "Your list is empty. Just like my lasagna pan. "
                    + "Are we done here, or are you going to add something?";
        } else {
            return "Ugh, here's what you've got so far:\n\n" + tasks.list()
                    + "\nCan we be done now?";
        }
    }
}
