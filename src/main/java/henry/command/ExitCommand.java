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
     * Ends the chat
     *
     * @param taskList instance of a TaskList class that contains
     *                 an array of tasks
     * @param ui instance of a Ui class that interacts with the user
     */
    public void execute(TaskList taskList, Ui ui) throws HenryException {
        System.out.println();
    }
}
