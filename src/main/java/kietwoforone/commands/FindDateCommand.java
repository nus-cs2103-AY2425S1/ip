package kietwoforone.commands;

import kietwoforone.exceptions.KieTwoForOneException;
import kietwoforone.storage.Storage;
import kietwoforone.tasks.TaskList;
import kietwoforone.ui.UI;

/**
 * Represents the command called when the user filters the task by date.
 */
public class FindDateCommand extends Command {

    private String date;

    /**
     * Constructor for the FindDateCommand object.
     *
     * @param date
     */
    public FindDateCommand(String date) {
        this.date = date;
    }

    /**
     * Executes the command to filter the tasks by date.
     * Throws a KieTwoForOne exception when user inputs an incorrect date format.
     *
     * @param tasks
     * @param ui
     * @param storage
     * @throws KieTwoForOneException
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws KieTwoForOneException {
        try {
            ui.showSameDate(tasks.getTaskList(), this.date);
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
     * Returns the string representation of the FindDateCommand object.
     *
     * @return String representation of the FindDateCommand object.
     */
    @Override
    public String toString() {
        return "Date found";
    }

}
