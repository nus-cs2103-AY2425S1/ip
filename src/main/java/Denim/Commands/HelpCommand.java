package denim.commands;

import denim.TaskList;
import denim.storage.TaskIo;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public final String returnFormat = String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s",
            DeadlineCommand.COMMAND_USAGE,
            TodoCommand.COMMAND_USAGE,
            EventCommand.COMMAND_USAGE,
            DeleteCommand.COMMAND_USAGE,
            ListCommand.COMMAND_USAGE,
            MarkCommand.COMMAND_USAGE,
            UnmarkCommand.COMMAND_USAGE);

    @Override
    public CommandResult execute(TaskList taskList, TaskIo taskIO) {
        return new CommandResult(returnFormat);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
