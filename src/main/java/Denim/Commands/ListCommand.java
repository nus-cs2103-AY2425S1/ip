package denim.commands;

import denim.TaskList;
import denim.storage.TaskIo;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_USAGE = "list";
    @Override
    public CommandResult execute(TaskList taskList, TaskIo taskIo) {
        String returnMessage = taskList.printList();
        return new CommandResult(returnMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
