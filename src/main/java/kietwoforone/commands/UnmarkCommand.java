package kietwoforone.commands;

import kietwoforone.exceptions.KieTwoForOneException;
import kietwoforone.storage.Storage;
import kietwoforone.tasks.TaskList;
import kietwoforone.ui.UI;

/**
 * Represents the command called when the user wishes to unmark an incomplete task.
 */
public class UnmarkCommand extends Command {

    private int position;

    /**
     * Constructor for the UnmarkCommand object.
     *
     * @param position
     */
    public UnmarkCommand(int position) {
        this.position = position;
    }

    /**
     * Executes the command to unmark the task at the position specified by the user.
     * Throws a KieTwoForOne exception when there is no task at the specified position.
     * Throws a KieTwoForOne exception when the file to be saved to does not exist.
     *
     * @param tasks
     * @param ui
     * @param storage
     * @throws KieTwoForOneException
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws KieTwoForOneException {
        String unmarkedTask = tasks.unmarkTask(this.position);
        ui.showUnmarkTask(unmarkedTask);
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
     * Returns the string representation of the UnmarkCommand object
     *
     * @return String representation of the UnmarkCommand object.
     */
    @Override
    public String toString() {
        return "Task unmarked";
    }

}
