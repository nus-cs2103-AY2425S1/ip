package denim.commands;

import denim.TaskList;
import denim.storage.WriteTaskFile;

/**
 * Represents a Help command that can be executed.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    @Override
    public CommandResult execute(TaskList taskList, WriteTaskFile writeTaskFile) {
        return new CommandResult(String.join("\n", CommandUsages.COMMAND_USAGES),
                CommandStatus.COMMAND_SUCCESSFUL);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
