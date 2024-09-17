package genji.command;

import genji.task.TaskList;
import genji.Ui;
import genji.Storage;

/**
 * A class that deals with help command
 */
public class HelpCommand extends Command {
    private String response;

    /**
     * Calls the help method in Ui class
     * @param list Task list to be modified
     * @param ui Ui for display
     * @param s Storage for saving
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage s) {
        response = ui.help();
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
