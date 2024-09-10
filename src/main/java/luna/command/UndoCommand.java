package luna.command;

import luna.LunaException;
import luna.Storage;
import luna.TaskList;

public class UndoCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage) throws LunaException {
        return "I have undone the last command";
    }

    @Override
    public String undo(TaskList tasks, Storage storage) {
        return "undo command";
    }


}
