package demurebot.command;

import demurebot.DemureBotException;
import demurebot.task.TaskList;
import demurebot.ui.Ui;

/**
 * Represents a command to handle invalid commands.
 */
public class InvalidCommand extends Command {
    /**
     * Constructs an InvalidCommand object.
     */
    public InvalidCommand() {
    }

    /**
     * Executes the command to handle invalid commands.
     *
     * @param list TaskList containing the list of tasks.
     * @param ui Ui object to interact with the user.
     * @return Error message to inform the user of the invalid command.
     * @throws DemureBotException If an invalid command is entered.
     */
    @Override
    public String execute(TaskList list, Ui ui) throws DemureBotException {
        throw new DemureBotException(
                "Invalid command\nCreate a new task starting with the command todo, deadline or event.\n"
        );
    }
}
