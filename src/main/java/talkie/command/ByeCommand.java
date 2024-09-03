package talkie.command;

import java.io.IOException;

import talkie.components.Storage;
import talkie.components.Ui;
import talkie.task.TaskList;

/**
 * Represents a command to exit the Talkie application.
 * <p>
 * The {@code ByeCommand} is responsible for saving the current task list data to persistent storage
 * and displaying a goodbye message to the user before the application exits.
 * </p>
 */
public class ByeCommand extends Command {

    /**
     * Executes the {@code ByeCommand}, which saves the current task list to storage
     * and displays a goodbye message to the user.
     * <p>
     * If an error occurs during the data-saving process, the method returns an error message instead.
     * </p>
     *
     * @param tasks   The task list containing all current tasks.
     * @param ui      The UI component used to display messages to the user.
     * @param storage The storage component used to save task data.
     * @return A string containing the goodbye message if the data is successfully saved,
     *         or an error message if the saving process fails.
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

    /**
     * Indicates that this command will terminate the application.
     * <p>
     * The {@code ByeCommand} always returns {@code true} because it is intended to exit the program.
     * </p>
     *
     * @return {@code true}, as this command ends the program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
