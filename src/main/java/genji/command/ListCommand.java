package genji.command;

import genji.task.TaskList;
import genji.Ui;
import genji.Storage;

/**
 * A class that deals with displaying task list command
 */
public class ListCommand extends Command {
    private String response;

    /**
     * Calling the showList method in task list class
     * @param list Task list to be modified
     * @param ui Ui for display
     * @param s Storage for saving
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage s) {
        response = list.showList();
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
     * Get response message for GUI
     * @return Formatted string
     */
    @Override
    public String getResponse() {
        return response;
    }
}
