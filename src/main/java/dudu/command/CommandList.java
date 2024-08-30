package dudu.command;

import dudu.command.Command;
import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

public class CommandList extends Command {
    /**
     * Executes the exit command by displaying a goodbye message to the user.
     *
     * @param taskList The task list (not used in this command).
     * @param ui The user interface to display the goodbye message.
     * @param storage The storage (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage){
        ui.printTasks(taskList);
    }

    /**
     * Indicates that this command will not cause the application to exit.
     *
     * @return false, as this command always does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Compares this CommandList to another object for equality. Two CommandList
     * objects are considered equal if they are of the same class.
     *
     * @param o The object to compare this CommandList with.
     * @return true if the other object is a CommandList, false otherwise.
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
