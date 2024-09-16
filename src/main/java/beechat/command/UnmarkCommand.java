package beechat.command;

import beechat.util.Storage;
import beechat.task.Task;
import beechat.task.TaskList;
import beechat.util.Ui;

import java.io.IOException;

/**
 * Represents a command to unmark a task from the task list as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a UnmarkCommand with the specified task index.
     *
     * @param taskIndex The 1-based index of the task to be unmarked.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1; //convert to 0-based indexing
    }

    /**
     * Executes the unmark command, which unmarks the task at the specified index from the task list
     * and updates the storage.
     *
     * @param tasks   The TaskList object that contains all tasks.
     * @param ui      The Ui object that handles all user interactions.
     * @param storage The Storage object that saves and loads tasks.
     * @throws IOException Handles error that occurs while executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.getTask(taskIndex);
        task.unmark();
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
        storage.saveTasks(tasks.getTasks());
    }
}