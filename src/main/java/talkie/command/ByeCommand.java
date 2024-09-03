package talkie.command;

import java.io.IOException;

import talkie.components.Storage;
import talkie.components.Ui;
import talkie.task.TaskList;

/**
 * Represents a command to exit the Talkie application.
 * This command saves the current task list data to storage
 * and displays a goodbye message to the user before exiting.
 */
public class ByeCommand extends Command {

    /**
     * Executes the ByeCommand which saves the task list to storage
     * and displays a goodbye message to the user.
     *
     * @param tasks   The task list containing all current tasks.
     * @param ui      The UI component used to display messages to the user.
     * @param storage The storage component used to save task data.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.saveData(tasks);
            return ui.byeMessage();
        } catch (IOException e) {
            return "Oops! Something went wrong when saving the data!";
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
