package denim.commands;

import denim.TaskList;
import denim.storage.WriteTaskFile;

/**
 * Represents an exit command that can be executed.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    @Override
    public CommandResult execute(TaskList taskList, WriteTaskFile writeTaskFile) {
        return new CommandResult("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
