package cheese.command;

import cheese.Storage;
import cheese.TaskList;
import cheese.Ui;
import cheese.exception.CheeseException;

/**
 * Abstract parent class for all commands
 */
public abstract class Command {
    private final boolean exitChat;

    public Command() {
        exitChat = false;
    }

    /**
     * Check if the program should exit
     * @return boolean
     */
    public boolean isExit() {
        return exitChat;
    }

    /**
     * Function to call the command
     * @param tasks list of tasks
     * @param ui format response
     * @param storage store data
     * @throws CheeseException in case something breaks
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws CheeseException;
}
