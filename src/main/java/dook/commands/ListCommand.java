package dook.commands;

import java.io.IOException;

import dook.DookException;
import dook.storage.Storage;
import dook.tasks.TaskList;
import dook.ui.Ui;

/**
 * The command that lists all the tasks in the TaskList.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand, displaying all tasks in the TaskList.
     * Throws an exception if the TaskList is empty.
     *
     * @param tasks The TaskList containing the tasks to be listed.
     * @param ui The Ui object that handles user interactions and displays task information.
     * @param storage The Storage object that handles saving the TaskList (not used in this command).
     * @return A string listing all tasks with their indices, or an exception message if no tasks are present.
     * @throws DookException If the TaskList is empty.
     * @throws IOException If an I/O error occurs while interacting with the UI (not expected in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DookException, IOException {
        exitIfEmpty(tasks);
        return printMessages(tasks, ui);
    }

    private void exitIfEmpty(TaskList tasks) throws DookException {
        if (tasks.isEmpty()) {
            throw new DookException("No tasks");
        }
    }

    private String printMessages(TaskList tasks, Ui ui) throws DookException {
        ui.separate();
        String message = "";

        for (int i = 0; i < tasks.numOfTasks(); i++) {
            ui.showMessage((i + 1) + ". " + tasks.getTask(i));
            message = message.concat((i + 1) + ". " + tasks.getTask(i) + "\n");
        }

        ui.separate();

        return message;
    }
}
