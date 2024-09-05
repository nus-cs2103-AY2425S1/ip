package rizzler.command;

import rizzler.Storage;
import rizzler.task.TaskLog;

/**
 * Null command meant to represent the lack of a command due to errors in user input.
 */
public class NullCommand extends Command {

    /**
     * Constructor for NullCommand object.
     * @param string Description of the error in user input
     */
    public NullCommand(String string) {
        super(string);
    }

    /**
     * Executes nothing, but prints the description of the user error for rectification.
     * @param storage <code>Storage</code> object instantiated in main <code>Rizzler</code> class.
     * @param taskLog <code>TaskLog</code> object instantiated in main <code>Rizzler</code> class.
     * @return Line informing user of how to fix their input.
     */
    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        return new String[] {getTextInput()};
    }
}
