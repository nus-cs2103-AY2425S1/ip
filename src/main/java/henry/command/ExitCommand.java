package henry.command;

import henry.util.TaskList;
import henry.util.Ui;

/**
 * Deals with exiting the chat.
 */
public class ExitCommand extends Command {

    /**
     * Returns a string informing user that the chat has ended.
     *
     * @param taskList Instance of a TaskList class that contains
     *                 an array of tasks.
     * @param ui Instance of an Ui class that interacts with the user.
     * @return A string that signifies the end of chat.
     */
    public String execute(TaskList taskList, Ui ui) {
        return ui.bye();
    }
}
