package rizzler.command;

import rizzler.Storage;
import rizzler.task.TaskLog;

/**
 * Represents the user's request for help in using the chatbot.
 */
public class HelpCommand extends Command {

    /**
     * Constructor for HelpCommand.
     */
    public HelpCommand() {
        super();
    }

    /**
     * Prints out some lines of information that may be helpful to a new user.
     * @param storage <code>Storage</code> object instantiated in main <code>Rizzler</code> class.
     * @param taskLog <code>TaskLog</code> object instantiated in main <code>Rizzler</code> class.
     * @return Information on common commands and where to read the documentation.
     */
    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        return new String[] {"sorry, this command is not available yet."};
    }

}
