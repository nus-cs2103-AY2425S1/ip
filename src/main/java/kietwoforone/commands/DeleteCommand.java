package kietwoforone.commands;

import kietwoforone.exceptions.KieTwoForOneException;
import kietwoforone.storage.Storage;
import kietwoforone.tasks.Task;
import kietwoforone.tasks.TaskList;
import kietwoforone.ui.UI;

/**
 * Represents the command called when the user deletes a task from the task list.
 */
public class DeleteCommand extends Command {

    int position;

    /**
     * Constructor for the DeleteCommand object.
     *
     * @param position
     */
    public DeleteCommand(int position) {
        this.position = position;
    }

    /**
     * Executes the command that deletes a task from the task list.
     * Throws a KieTwoForOne exception when the index specified does not hold a task.
     * Throws a KieTwoForOne exception when saving to a file that doesn't exist.
     *
     * @param tasks
     * @param ui
     * @param storage
     * @throws KieTwoForOneException
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws KieTwoForOneException {
        Task deletedTask = tasks.deleteTask(this.position);
        ui.showDeleteTask(tasks.getTaskList(), deletedTask);
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
     * @return Boolean to determine whether to close the chatbot.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the string representation of the DeleteCommand object.
     *
     * @return String representation of the DeleteCommand object.
     */
    @Override
    public String toString() {
        return "Task deleted";
    }

}
