package demurebot.command;

import demurebot.DemureBotException;
import demurebot.task.TaskList;
import demurebot.ui.Ui;

public class InvalidCommand extends Command {
    public InvalidCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList list, Ui ui) throws DemureBotException {
        throw new DemureBotException(
                "Invalid command\nCreate a new task starting with the command todo, deadline or event.\n"
        );

    }
}
