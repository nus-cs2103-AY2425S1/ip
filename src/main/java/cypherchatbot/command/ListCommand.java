package cypherchatbot.command;

import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;

/**
 * Represents a command to print out all the tasks.
 * This command retrieves and displays the current list of tasks using the Ui interface.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand by retrieving the list of tasks from the TaskList
     * and displaying them to the user via the Ui.
     *
     * @param tasks   The TaskList containing the tasks to be listed.
     * @param ui      The Ui used to interact with the user and display the tasks.
     * @param storage The Storage used for saving tasks, though not used in this command.
     */
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        String task = tasks.toString();
        ui.output(task);
    };

    /**
     * Returns false indicating that this command does not cause the application to exit.
     */
    public boolean isExit() {
        return false;
    }
}
