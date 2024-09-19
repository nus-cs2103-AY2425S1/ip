package bing.command;

import bing.storage.Storage;
import bing.task.Task;
import bing.task.TaskList;
import bing.task.TaskStatus;
import bing.ui.Ui;
import java.io.IOException;


/**
 * Represents a command to unmark a task as undone.
 */
public class UnmarkCommand implements Command {
    private int index;

    /**
     * Constructs an UnmarkCommand with the specified index.
     *
     * @param index The index of the task to unmark as undone.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking the task at the specified index as undone,
     * updating the user interface, and saving the changes to storage.
     *
     * @param tasks The list of tasks to operate on.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save or load tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.get(index);
        task.setStatus(TaskStatus.UNDONE);
        storage.save(tasks.getTasks());
        return ui.showTasks(tasks);
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
