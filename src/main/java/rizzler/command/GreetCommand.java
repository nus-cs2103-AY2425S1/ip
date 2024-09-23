package rizzler.command;

import rizzler.Storage;
import rizzler.task.TaskLog;

/**
 * Greets the user when they first launch the chatbot.
 */
public class GreetCommand extends Command {

    /**
     * Constructs a greet command.
     */
    public GreetCommand() {
        super();
    }

    /**
     * Serves as storage for the greeting lines.
     * No execution or manipulation of storage/taskLog is done here.
     * @param storage <code>Storage</code> object instantiated in main <code>Rizzler</code> class.
     * @param taskLog <code>TaskLog</code> object instantiated in main <code>Rizzler</code> class.
     * @return Lines of a welcome message to be printed to the user.
     */
    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        return new String[] {"how ya' doin. i'm the rizzler.",
                "how may i be of service to you today?"};
    }
}
