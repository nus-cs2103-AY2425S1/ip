package wolfie.command;

import java.io.IOException;

import wolfie.exception.WolfieException;
import wolfie.task.Task;
import wolfie.task.TaskList;
import wolfie.util.Storage;
import wolfie.util.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand object to delete a task from the task list.
     *
     * @param arguments The arguments passed to the command.
     */
    public DeleteCommand(String arguments) throws WolfieException {
        try {
            this.index = Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            throw new WolfieException("⚠ Please enter a valid task number, not the description.");
        }
    }


    /**
     * Executes the delete command to delete a task from the task list.
     *
     * @param tasks The task list to delete the task from.
     * @param ui The user interface to display messages.
     * @param storage The storage to save the task list to.
     * @return The message to show the user.
     * @throws IOException If there is an error saving the task list.
     * @throws WolfieException If the task number is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, WolfieException {
        if (index < 0 || index >= tasks.size()) {
            throw new WolfieException("⚠ Invalid task number. Please use existing numbers and not the description.");
        }
        Task removedTask = tasks.remove(index);
        storage.save(tasks);
        return ui.showTaskRemoved(removedTask, tasks.size());
    }
}
