package beechat.command;

import beechat.util.Storage;
import beechat.task.Task;
import beechat.task.TaskList;
import beechat.util.Ui;

import java.io.IOException;

/**
 * Represents a command to mark a task from the task list as done.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param taskIndex The 1-based index of the task to be marked.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1; // convert to 0-based indexing
    }

    /**
     * Executes the mark command, which marks the task at the specified index from the task list
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
        task.mark();
        System.out.println("Nice! I've marked this task as done:\n" + task);
        storage.saveTasks(tasks.getTasks());
    }
}