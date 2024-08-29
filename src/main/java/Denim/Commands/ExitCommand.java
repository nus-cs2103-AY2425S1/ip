package denim.commands;

import denim.TaskList;
import denim.storage.TaskIo;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    @Override
    public CommandResult execute(TaskList taskList, TaskIo taskIo) {
        return new CommandResult("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
