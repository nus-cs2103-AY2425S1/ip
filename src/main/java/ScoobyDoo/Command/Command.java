package ScoobyDoo.Command;

import ScoobyDoo.exception.InputFormatException;
import ScoobyDoo.task.TaskList;
import ScoobyDoo.UI.UI;
import ScoobyDoo.storage.Storage;

public abstract class Command {
    public abstract String execute (TaskList taskList, UI ui, Storage storage)  throws InputFormatException;
}
