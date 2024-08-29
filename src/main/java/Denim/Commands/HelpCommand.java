package Denim.Commands;

import Denim.Exceptions.DenimException;
import Denim.TaskList;
import Denim.Storage.TaskIO;
import Denim.Tasks.Task;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public final String returnFormat = String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s",
            DeadlineCommand.USAGE,
            TodoCommand.USAGE,
            EventCommand.USAGE,
            DeleteCommand.USAGE,
            ListCommand.USAGE,
            MarkCommand.USAGE,
            UnmarkCommand.USAGE);

    @Override
    public CommandResult execute(TaskList taskList, TaskIO taskIO) {
        return new CommandResult(returnFormat);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
