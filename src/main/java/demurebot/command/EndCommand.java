package demurebot.command;

import demurebot.task.TaskList;
import demurebot.ui.Ui;

/**
 * Represents a command to end the program.
 */
public class EndCommand extends Command {
    /**
     * Constructor for EndCommand.
     */
    public EndCommand() {
    }

    /**
     * Ends the program.
     *
     * @param list The task list.
     * @param ui The user interface to display the result of the command.
     * @return The result of the command.
     */
    @Override
    public String execute(TaskList list, Ui ui) {
        return ui.displayEnd();
    }
}
