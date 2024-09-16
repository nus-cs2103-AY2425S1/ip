package commands;

import java.util.ArrayList;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import tasks.TodoTask;
import ui.Ui;

/**
 * Represents a command to add a to-do task to the task list.
 * This command adds a specified {@link TodoTask} to the list of tasks,
 * prints confirmation messages to the user, and updates the storage.
 */
public class TodoCommand extends Command {

    private TodoTask task;

    /**
     * Constructs a TodoCommand with the specified to-do task.
     *
     * @param task The {@link TodoTask} to be added to the task list.
     */
    public TodoCommand(TodoTask task) {
        this.task = task;
    }

    /**
     * Checks whether this command is an exit command.
     * This command is not an exit command, so it returns false.
     *
     * @return false, as this command does not signify that the application should terminate.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the to-do command by adding the to-do task to the task list,
     * printing the details of the added task, and updating the storage.
     *
     * @param tasks The current list of tasks. This parameter allows the command
     *              to modify the task list by adding the to-do task.
     * @param ui The user interface component. This parameter is not used in this method.
     * @param storage The storage component. This parameter is used to update the storage
     *                with the modified task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> list = tasks.getTasks();
        list.add(this.task);
        StringBuilder output = new StringBuilder();
        output.append("Got it. I've added this task:\n");
        output.append(this.task).append("\n");
        output.append(String.format("Now you have %d tasks in the list.%n", list.size()));
        storage.updateStorage(tasks);
        return output.toString();
    }
}
