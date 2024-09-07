package genji.command;

import genji.task.TaskList;
import genji.Ui;
import genji.Storage;

/**
 * A class that deals with exit command
 */
public class ExitCommand extends Command {
    private String response;

    /**
     * Calling the bye method in Ui class
     * @param list Task list to be modified
     * @param ui Ui for display
     * @param s Storage for saving
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage s) {
        response = ui.bye();
    }

    /**
     * Distinguishes if it is a bye command
     * @return Does not exit
     */
    @Override
    public boolean isExit() {
        return true;
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
