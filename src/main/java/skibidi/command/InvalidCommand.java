package skibidi.command;

import skibidi.utils.Storage;
import skibidi.utils.TaskList;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {
    public static final String COMMAND_WORD = "invalid";

    public InvalidCommand() {
        super(COMMAND_WORD);
    }

    @Override
    public String execute(Storage storage, TaskList taskList) {
        return "No rizz, no clue what you're yapping about :-(";
    }
}
