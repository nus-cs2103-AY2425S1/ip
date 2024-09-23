package rizzler.command;

import rizzler.Storage;
import rizzler.task.TaskLog;

/**
 * Represents the ending of chatbot execution.
 */
public class ByeCommand extends Command {

    /**
     * Constructor for an ByeCommand object.
     */
    public ByeCommand() {
        super();
    }

    /**
     * Sets the flag for the program to halt with this command.
     * @param storage <code>Storage</code> object instantiated in main <code>Rizzler</code> class.
     * @param taskLog <code>TaskLog</code> object instantiated in main <code>Rizzler</code> class.
     * @return Nothing to be printed, functions as a null placeholder.
     */
    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        setShouldEnd(true);
        return new String[0];
    }
}
