package kobe.command;

import kobe.KobeException;
import kobe.task.Task;
import kobe.task.TaskList;
import kobe.util.Storage;
import kobe.util.Ui;

import java.io.IOException;

/**
 * Represents a command to mark a task as not done in the Kobe chatbot application.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param taskIndex The 1-based index of the task to be marked as not done.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1; // Convert to 0-based index
    }

    /**
     * Executes the unmark command, which marks the specified task as not done and updates the storage.
     *
     * @param tasks   The TaskList object containing all tasks.
     * @param ui      The Ui object responsible for user interactions.
     * @param storage The Storage object responsible for saving and loading tasks.
     * @throws IOException   If an error occurs during file operations while executing the command.
     * @throws KobeException If the task number is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, KobeException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new KobeException("Invalid task number: " + (taskIndex + 1) + ". Please enter a valid task number between 1 and " + tasks.size() + ".");
        }
        Task task = tasks.getTask(taskIndex);
        task.markAsNotDone();
        ui.setResponse("OK! I've marked this task as not done:\n  " + task);
        storage.save(tasks.getTasks());
    }
}
