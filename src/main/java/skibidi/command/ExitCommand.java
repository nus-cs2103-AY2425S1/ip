package skibidi.command;

import skibidi.utils.Storage;
import skibidi.utils.TaskList;

/**
 * Represents an exit command.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    public ExitCommand() {
        super(COMMAND_WORD);
    }

    @Override
    public String execute(Storage storage, TaskList taskList) {
        return "Peace out. Hope to catch you yapping in SkibidiVille again soon!";
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
