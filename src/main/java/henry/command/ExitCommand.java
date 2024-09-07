package henry.command;

import henry.HenryException;
import henry.util.TaskList;
import henry.util.Ui;

/**
 * Deals with exiting the chat
 */
public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Returns a string informing user that the chat has ended
     *
     * @param taskList instance of a TaskList class that contains
     *                 an array of tasks
     * @param ui instance of an Ui class that interacts with the user
     * @return a string that signifies the end of chat
     */
    public String execute(TaskList taskList, Ui ui) throws HenryException {
        return ui.bye();
    }
}
