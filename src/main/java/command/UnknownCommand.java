package command;

import task.TaskList;

/**
 * Command created by unrecognized commands
 *
 * @author IsaacPangTH
 */
public class UnknownCommand extends Command {

    String command;

    /**
     * Constructor for<code>UnknownCommand</code>
     *
     * @param command Unrecognized command
     */
    public UnknownCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList list) {
        return him.Ui.sayInvalidCommand(command);
    }
}
