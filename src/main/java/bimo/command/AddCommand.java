package bimo.command;

import bimo.Storage;
import bimo.TaskList;
import bimo.Ui;
import bimo.tasks.Task;

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
        tasks.addTask(this.task);
        storage.appendToFile(this.task);
        int length = tasks.getLength();
        return ui.sendAddTaskMessage(length, task);
    }
}
