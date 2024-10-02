package bing.command;

import bing.storage.Storage;
import bing.task.Task;
import bing.task.TaskList;
import bing.ui.Ui;

import java.io.IOException;


/**
 * Represents a command that adds a task to the task list.
 */
public abstract class AddCommand implements Command {

    protected final Task task;


    /**
     * Constructs an AddCommand with the given task.
     *
     * @param task the task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command by adding the task, displaying the tasks, and saving them.
     *
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage for tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException{
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        return ui.showTasks(tasks);

    }

    /**
     * Returns whether this command causes the application to exit (it does not).
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
