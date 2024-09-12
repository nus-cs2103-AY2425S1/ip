package cheese.command;

import cheese.Storage;
import cheese.TaskList;
import cheese.Ui;

/**
 * Command to call to exit program
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    /**
     * Changes isExit() to true to end program
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Simple bye
     * @param tasks list of tasks
     * @param ui format response
     * @param storage store data
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.bye();
    }
}
