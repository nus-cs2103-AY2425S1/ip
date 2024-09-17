package genji.command;

import genji.task.TaskList;
import genji.Ui;
import genji.Storage;

/**
 * A class that deals with acquiring task on specific day command
 */
public class DateCommand extends Command {
    private String date;
    private String response;

    /**
     * Constructor of date command
     * @param date Date concerning which task to be acquired
     */
    public DateCommand(String date) {
        this.date = date;
    }

    /**
     * Calls the checkDate method in task list class
     * @param list Task list to be modified
     * @param ui Ui for display
     * @param s Storage for saving
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage s) {
        response = ui.date(list.checkDate(date));
    }

    /**
     * Distinguishes if it is a bye command
     * @return Does not exit
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Gets response message for GUI
     * @return Formatted string
     */
    @Override
    public String getResponse() {
        return response;
    }
}
