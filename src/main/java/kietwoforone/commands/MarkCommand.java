package kietwoforone.commands;

import kietwoforone.exceptions.KieTwoForOneException;
import kietwoforone.storage.Storage;
import kietwoforone.tasks.TaskList;
import kietwoforone.ui.UI;

/**
 * Represents the command called when the user marks a task as complete.
 */
public class MarkCommand extends Command {

    private int position;

    /**
     * Constructor for the MarkCommand object.
     *
     * @param position
     */
    public MarkCommand(int position) {
        this.position = position;
    }

    /**
     * Executes the command to mark the task at the position specified by the user.
     * Throws a KieTwoForOne exception when there is no task at the specified position.
     * Throws a KieTwoForOne exception when the file being saved to does not exist.
     *
     * @param tasks
     * @param ui
     * @param storage
     * @throws KieTwoForOneException
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws KieTwoForOneException {
        String markedTask = tasks.markTask(position);
        ui.showMarkTask(markedTask);
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
     * Returns the string representation of the MarkCommand object.
     *
     * @return String representation of the MarkCommand object.
     */
    @Override
    public String toString() {
        return "Task marked";
    }

}
