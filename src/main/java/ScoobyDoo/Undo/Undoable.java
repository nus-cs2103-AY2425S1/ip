package ScoobyDoo.Undo;

import ScoobyDoo.UI.UI;
import ScoobyDoo.storage.Storage;
import ScoobyDoo.task.TaskList;

public interface Undoable {
    String undo(UI ui, TaskList taskList, Storage storage);
    String redo(UI ui, TaskList taskList, Storage storage);
}
