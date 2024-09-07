package command;
import fridayException.FridayException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;
import ui.UiGui;

import java.io.IOException;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to the task list, displays a message to the user, and saves the task list to file.
     *
     * @param tasks The task list to be modified by the command.
     * @param ui The user interface to display messages to the user.
     * @param storage The storage to save the task list to file.
     * @throws FridayException If an error occurs during execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FridayException {
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new FridayException("Error saving tasks to file.");
        }
    }

    @Override
    public String executeGui(TaskList tasks, UiGui gui, Storage storage) throws FridayException {
        tasks.addTask(task);

        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new FridayException("Error saving tasks to file.");
        }
        return gui.showTaskAdded(task, tasks.size());
    }
}
