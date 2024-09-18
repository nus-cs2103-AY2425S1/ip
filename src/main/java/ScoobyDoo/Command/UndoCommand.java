package ScoobyDoo.Command;

import ScoobyDoo.UI.UI;
import ScoobyDoo.exception.InputFormatException;
import ScoobyDoo.storage.Storage;
import ScoobyDoo.task.TaskList;

public class UndoCommand extends Command{
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws InputFormatException {
        return taskList.undoHistory.undo(ui, taskList, storage);
    }
}
