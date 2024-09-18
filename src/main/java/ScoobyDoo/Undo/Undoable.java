package ScoobyDoo.Undo;

import ScoobyDoo.UI.UI;
import ScoobyDoo.exception.InputFormatException;
import ScoobyDoo.storage.Storage;
import ScoobyDoo.task.TaskList;

public interface Undoable {
    public String undo(UI ui, TaskList taskList, Storage storage);
    public String redo(UI ui, TaskList taskList, Storage storage);
}
