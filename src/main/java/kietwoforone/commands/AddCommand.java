package kietwoforone.commands;

import kietwoforone.exceptions.KieTwoForOneException;
import kietwoforone.storage.Storage;
import kietwoforone.tasks.Task;
import kietwoforone.tasks.TaskList;
import kietwoforone.ui.UI;

/**
 * Represents the command called when a new task is added to the task list.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Constructor for the AddCommand Object.
     *
     * @param task
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add a task to the tasklist.
     * Throws a KieTwoForOne exception when there is no file to save date.
     *
     * @param tasks
     * @param ui
     * @param storage
     * @throws KieTwoForOneException
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws KieTwoForOneException {
        tasks.addTasks(this.task);
        ui.showAddTasks(tasks.getTaskList(), this.task);
        try {
            storage.saveFile(tasks.getTaskList());
        } catch (KieTwoForOneException e) {
            throw new KieTwoForOneException(e.getMessage());
        }
    }

    /**
     * Returns a boolean to determine whether to close the chatbot.
     * True if the command closes the chatbot and false otherwise.
     *
     * @return Boolean to determine whether to close the chatbot
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the string representation of the AddCommand object.
     *
     * @return String representation of the AddCommand object.
     */
    @Override
    public String toString() {
        return "Task added";
    }

}
