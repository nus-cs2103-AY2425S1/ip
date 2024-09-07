package karen.commands;

import karen.tasks.TaskList;
import karen.util.Ui;

/**
 * Handles printing the exit message and stopping the main loop
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui) {
        return ui.sayGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
