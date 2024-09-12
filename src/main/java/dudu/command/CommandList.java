package dudu.command;

import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

/**
 * Represents a list tasks user command into the chatbot
 */
public class CommandList extends Command {
    /**
     * Displays a list of tasks to the user
     *
     * @param taskList The task list
     * @param ui The user interface to display the goodbye message
     * @param storage The storage
     * @return Successful listing of task response
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        return ui.printTasks(taskList);
    }

    /**
     * Compares this CommandList to another object for equality. Two CommandList
     * objects are considered equal if they are of the same class
     *
     * @param o The object to compare this CommandList with
     * @return true if the other object is a CommandList, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || !(o instanceof CommandList)) {
            return false;
        }
        return true;
    }
}
