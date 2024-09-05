package rizzler.command;

import rizzler.Storage;
import rizzler.task.TaskLog;

/**
 * Bids farewell to the user after the user has said bye.
 */
public class ByeCommand extends Command {

    /**
     * Constructor for a <code>ByeCommand</code> object.
     */
    public ByeCommand() {
        super();
    }

    /**
     * Outputs <code>String</code> to be printed in response.
     * Nothing is executed on <code>storage</code> or <code>taskLog</code>.
     * @param storage <code>Storage</code> object instantiated in main <code>Rizzler</code> class.
     * @param taskLog <code>TaskLog</code> object instantiated in main <code>Rizzler</code> class.
     * @return <code>String</code> saying goodbye to the user.
     */
    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        return new String[] {"aight bet, cya."};
    }
}