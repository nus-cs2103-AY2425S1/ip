package trackie.commands;

import trackie.storage.Storage;
import trackie.storage.TaskList;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {
    /**
     * Executes the invalid command.
     * Notifies the user that the command that they have typed in is invalid.
     *
     * @param tasklist The TaskList object used.
     * @param storage The Storage object used.
     */
    @Override
    public String execute(TaskList tasklist, Storage storage) {
        return "I don't know what command that is m8...\n\n" +
                "Try typing \"help\" to find out what are the available commands =)";
    }

}
