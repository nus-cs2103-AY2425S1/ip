package rizzler.command;

import rizzler.Storage;
import rizzler.task.TaskLog;

/**
 * Greets the user when they first launch the chatbot.
 */
public class GreetCommand extends Command {

    /**
     * Constructor for a greet command.
     */
    public GreetCommand() {
        super();
    }

    /**
     * No execution is done here: this serves as storage for the greeting lines.
     * @param storage <code>Storage</code> object instantiated in main <code>Rizzler</code> class.
     * @param taskLog <code>TaskLog</code> object instantiated in main <code>Rizzler</code> class.
     * @return Welcome messages to be printed to the user.
     */
    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        return new String[] {"how ya' doin. i'm the rizzler.",
                "how may i be of service to you today?"};
    }
}
