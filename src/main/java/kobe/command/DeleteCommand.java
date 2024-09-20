package kobe.command;

import kobe.util.Storage;
import kobe.task.Task;
import kobe.task.TaskList;
import kobe.util.Ui;

import java.io.IOException;

/**
 * Represents a command to delete a task from the task list in the Duke chatbot application.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param taskIndex The 1-based index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1; // Convert to 0-based index
    }

    /**
     * Executes the delete command, which removes a task from the task list and updates the storage.
     *
     * @param tasks   The TaskList object containing all tasks.
     * @param ui      The Ui object responsible for user interactions.
     * @param storage The Storage object responsible for saving and loading tasks.
     * @throws IOException If an error occurs during file operations while executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task removedTask = tasks.removeTask(taskIndex);
        ui.setResponse("Noted. I've removed this task:\n  " + removedTask + "\nNow you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks.getTasks());
    }
}
