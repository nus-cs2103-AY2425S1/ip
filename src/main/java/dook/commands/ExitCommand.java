package dook.commands;

import java.io.IOException;

import dook.DookException;
import dook.storage.Storage;
import dook.tasks.TaskList;
import dook.ui.Ui;

/**
 * The command that the user uses to exit the chatbot.
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand by saving the current TaskList state and
     * terminating the application.
     *
     * @param tasks The TaskList containing all current tasks to be saved.
     * @param ui The Ui object that handles user interactions and application exit.
     * @param storage The Storage object that handles saving the TaskList.
     * @return A farewell message indicating that the application is closing.
     * @throws DookException If an error occurs during command execution.
     * @throws IOException If an I/O error occurs while saving the TaskList.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DookException, IOException {
        assert tasks != null : "TaskList is null";
        storage.write(tasks);
        ui.exit();
        super.isExit = true;
        return "Bye. Hope to see you again soon!\n";
    }
}
