package bimo.command;

import bimo.tasks.Task;
import bimo.utils.Storage;
import bimo.utils.TaskList;
import bimo.utils.Ui;

/**
 * Creates a command that add tasks to list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Instantiates a command that adds user task.
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds specified task to user list.
     *
     * @param tasks List of user tasks.
     * @param ui User interface that interacts with users.
     * @param storage Storage that writes and load files.
     * @return Response of chatbot.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert task != null : "Task must not be null";
        assert tasks != null : "Task list must not be null";
        tasks.addTask(this.task);
        storage.appendToFile(this.task);
        int length = tasks.getLength();
        String response = ui.sendAddTaskMessage(length, task);
        return response;

    }
}
