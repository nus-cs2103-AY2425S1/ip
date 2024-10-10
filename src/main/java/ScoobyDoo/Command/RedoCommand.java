package ScoobyDoo.Command;

import ScoobyDoo.UI.UI;
import ScoobyDoo.storage.Storage;
import ScoobyDoo.task.TaskList;

public class RedoCommand extends Command{
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        return taskList.undoHistory.redo(ui, taskList, storage);
    }
}