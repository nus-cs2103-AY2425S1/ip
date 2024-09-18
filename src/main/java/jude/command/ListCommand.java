package jude.command;

import jude.JudeException;
import jude.Storage;
import jude.TaskList;

/**
 * Represents the class which contains the series of actions to display the current TaskList.
 */
public class ListCommand extends Command {

    /**
     * Lists the tasks in the TaskList. Displays the taskList onto the ui.
     * @param list that will return the string representation of the tasklist
     * @param storage
     * @throws JudeException if any one of the method call fails
     */
    @Override
    public void execute(TaskList list, Storage storage) throws JudeException {
        setMessage(list.toUiFormat());
    }

    @Override
    public String toString() {
        return "ListCommend";
    }
}
