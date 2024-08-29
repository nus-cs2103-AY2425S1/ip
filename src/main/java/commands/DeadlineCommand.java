package commands;

import tasks.DeadlineTask;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.util.ArrayList;

/**
 * Represents a command to add a deadline task to the task list.
 * This command adds a specified {@link DeadlineTask} to the list of tasks,
 * updates the storage, and prints relevant messages to the user.
 */
public class DeadlineCommand extends Command {

    private DeadlineTask task;

    /**
     * Constructs a DeadlineCommand with the specified deadline task.
     *
     * @param task The {@link DeadlineTask} to be added to the task list.
     */
    public DeadlineCommand(DeadlineTask task) {
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
     * Executes the deadline command by adding the deadline task to the task list,
     * printing the task details, and updating the storage.
     *
     * @param tasks The current list of tasks. This parameter allows the command
     *              to modify the task list by adding the deadline task.
     * @param ui The user interface component. This parameter is not used in this method.
     * @param storage The storage component. This parameter is used to update the storage
     *                with the modified task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> list = tasks.getTasks();
        list.add(this.task);
        System.out.println("\nEl Primo:");
        System.out.println("Got it. I've added this task:");
        System.out.println(this.task);
        System.out.printf("Now you have %d tasks in the list.%n", list.size());
        storage.updateStorage(tasks);
    }
}
