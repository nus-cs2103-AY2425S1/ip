package mummy.command;

import java.util.HashMap;

import mummy.task.TaskList;
import mummy.ui.MummyException;
import mummy.utility.Storage;


/**
 * Represents a command to list all tasks in the task list.
 * When executed, it displays the string representation of the task list.
 */
public final class ListCommand extends Command {

    public ListCommand(HashMap<String, String> arguments) {
        super(arguments);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        return taskList.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.LIST;
    }

    @Override
    public String undo(TaskList taskList, Storage storage) throws MummyException {
        throw new MummyException("`list` command cannot be undone");
    }
}
