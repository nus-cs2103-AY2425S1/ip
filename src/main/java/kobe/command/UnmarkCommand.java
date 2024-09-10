package kobe.command;

import kobe.util.Storage;
import kobe.task.Task;
import kobe.task.TaskList;
import kobe.util.Ui;

import java.io.IOException;

/**
 * Represents a command to unmark a task as not done in the Duke chatbot application.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param taskIndex The 1-based index of the task to be unmarked as not done.
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
     * @throws IOException If an error occurs during file operations while executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.getTask(taskIndex);
        task.markAsNotDone();
        ui.setResponse("OK, I've marked this task as not done yet:\n  " + task);
        storage.save(tasks.getTasks());
    }
}
