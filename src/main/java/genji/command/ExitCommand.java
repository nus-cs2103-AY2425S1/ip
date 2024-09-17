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
     * Calls the bye method in Ui class
     * @param list Task list to be modified
     * @param ui Ui for display
     * @param s Storage for saving
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage s) {
        response = ui.bye();
        assert response == "Bye. Hope to see you again soon!" : "Response not correct";
    }

    /**
     * Distinguishes if it is a bye command
     * @return Exit
     */
    @Override
    public boolean isExit() {
        return true;
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
