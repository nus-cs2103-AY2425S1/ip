package mummy.command;

import java.util.HashMap;

import mummy.task.TaskList;
import mummy.ui.MummyException;
import mummy.utility.Storage;

/**
 * Represents a command to find tasks based on a keyword.
 * Extends the Command class.
 */
public final class FindCommand extends Command {

    public FindCommand(HashMap<String, String> arguments) {
        super(arguments);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws MummyException {
        String keyword = this.getArgument("description");

        if (keyword == null) {
            throw new MummyException("Keyword must be provided");
        }

        return taskList
                .filter(task -> task.getDescription().contains(keyword))
                .toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.FIND;
    }

    @Override
    public String undo(TaskList taskList, Storage storage) throws MummyException {
        throw new MummyException("`find` command cannot be undone");
    }
}
