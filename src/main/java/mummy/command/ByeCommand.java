package mummy.command;

import java.util.HashMap;

import mummy.task.TaskList;
import mummy.ui.MummyException;
import mummy.utility.Storage;

/**
 * Represents a command to exit the program.
 * When executed, it displays a farewell message to the user.
 * This command is used to terminate the program.
 */
public final class ByeCommand extends Command {

    public ByeCommand(HashMap<String, String> arguments) {
        super(arguments);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        return "Bye. Hope to see you again soon!\n";
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.BYE;
    }

    @Override
    public String undo(TaskList taskList, Storage storage) throws MummyException {
        throw new MummyException("`bye` command cannot be undone");
    }
}
