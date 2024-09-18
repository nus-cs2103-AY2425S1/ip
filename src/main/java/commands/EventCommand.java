package commands;

import java.util.ArrayList;

import storage.Storage;
import tasks.EventTask;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command to add an event task to the task list.
 * This command adds a specified {@link EventTask} to the list of tasks,
 * prints confirmation messages to the user, and updates the storage.
 */
public class EventCommand extends Command {

    private EventTask task;

    /**
     * Constructs an EventCommand with the specified event task.
     *
     * @param task The {@link EventTask} to be added to the task list.
     */
    public EventCommand(EventTask task) {
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
     * Executes the event command by adding the event task to the task list,
     * printing the task details, and updating the storage.
     *
     * @param tasks The current list of tasks. This parameter allows the command
     *              to modify the task list by adding the event task.
     * @param ui The user interface component. This parameter is not used in this method.
     * @param storage The storage component. This parameter is used to update the storage
     *                with the modified task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> list = tasks.getTasks();
        list.add(this.task);
        storage.updateStorage(tasks);
        return "New event task! Grrr:\n" + this.task + "\n" + String.format("Now you have %d tasks to PUNCH!%n", list.size());
    }
}
