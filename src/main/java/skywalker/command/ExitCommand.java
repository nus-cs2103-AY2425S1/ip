package skywalker.command;

import skywalker.storage.Storage;
import skywalker.task.TaskList;
import skywalker.ui.Ui;

/**
 * Customise the command type of exit command.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command, and show a bye message
     *
     * @param tasks   The task list on which the command will operate.
     * @param ui      The UI object used to interact with the user.
     * @param storage The storage object used to save or load the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }


    @Override
    public boolean isExit() {
        return true;
    }
}
