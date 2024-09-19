package bing.command;

import bing.storage.Storage;
import bing.task.Task;
import bing.task.TaskList;
import bing.task.TaskStatus;
import bing.ui.Ui;
import java.io.IOException;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand implements Command {
    private int index;

    /**
     * Constructs a MarkCommand with the specified index.
     *
     * @param index The index of the task to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking the task at the specified index as done,
     * updating the user interface, and saving the changes to storage.
     *
     * @param tasks The list of tasks to operate on.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save or load tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(index);
        task.setStatus(TaskStatus.DONE);
        ui.showTasks(tasks);
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            ui.showError("Unable to save task.");
        }
    }

    /**
     * Indicates that this command does not cause the program to terminate.
     *
     * @return false, as this command does not result in program termination.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
