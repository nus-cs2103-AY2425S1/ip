package karen.commands;

import karen.tasks.TaskList;
import karen.util.Ui;

/**
 * Handles retrieving the contents of a <code>TaskList</code> and prints it to the screen
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui) {
        return ui.displayTaskList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
