package beechat.command;

import beechat.util.Storage;
import beechat.task.Task;
import beechat.task.TaskList;
import beechat.util.Ui;

import java.io.IOException;

/**
 * Represents a command to delete a task from the task list.
 *
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param taskIndex The 1-based index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1; // Convert to 0-based indexing
    }

    /**
     * Executes the delete command, which removes the task at the specified index from the task list
     * and updates the storage.
     *
     * @param tasks   The TaskList object that contains all tasks.
     * @param ui      The Ui object that handles all user interactions.
     * @param storage The Storage object that saves and loads tasks.
     * @throws IOException If an error occurs while executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task removedTask = tasks.removeTask(taskIndex);
        System.out.println("Ok, I've removed this task:\n" + removedTask);
        storage.saveTasks(tasks.getTasks());
    }
}