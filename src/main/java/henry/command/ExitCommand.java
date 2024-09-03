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
     * Returns an empty line
     *
     * @param taskList instance of a TaskList class that contains
     *                 an array of tasks
     * @param ui instance of a Ui class that interacts with the user
     * @return an empty line
     */
    public String execute(TaskList taskList, Ui ui) throws HenryException {
        return ui.bye();
    }
}
