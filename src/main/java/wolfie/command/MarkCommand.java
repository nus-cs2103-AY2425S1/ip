package wolfie.command;

import java.io.IOException;

import wolfie.exception.WolfieException;
import wolfie.task.Task;
import wolfie.task.TaskList;
import wolfie.util.Storage;
import wolfie.util.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(String arguments) {
        this.index = Integer.parseInt(arguments) - 1;
    }

    /**
     * Executes the mark command to mark a task as done.
     *
     * @param tasks The task list to mark the task in.
     * @param ui The user interface to display messages.
     * @param storage The storage to save the task list to.
     * @return The message to show the user.
     * @throws IOException If there is an error saving the task list.
     * @throws WolfieException If the task number is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, WolfieException {
        if (index < 0 || index >= tasks.size()) {
            throw new WolfieException("Invalid task number. Please use existing numbers and not the description.");
        }
        Task task = tasks.get(index);
        task.markAsDone();
        storage.save(tasks);
        return ui.showTaskMarked(task);
    }
}
