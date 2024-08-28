package demurebot.command;

import demurebot.DemureBotException;
import demurebot.task.TaskList;
import demurebot.ui.Ui;

/**
 * Represents a command that can be executed by the bot.
 */
public abstract class Command {
    private final boolean isExit;

    /**
     * Constructor for Command.
     *
     * @param isExit Boolean value to determine if the command is an exit command.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Returns a boolean value to determine if the command is an exit command.
     *
     * @return Boolean value to determine if the command is an exit command.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes the command.
     *
     * @param list TaskList object that contains the list of tasks.
     * @param ui Ui object that handles the user interface.
     * @throws DemureBotException If an error occurs during the execution of the command.
     */
    public abstract void execute(TaskList list, Ui ui) throws DemureBotException;
}
