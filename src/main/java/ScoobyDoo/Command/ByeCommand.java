package ScoobyDoo.Command;

import java.time.LocalDateTime;

import ScoobyDoo.UI.UI;
import ScoobyDoo.exception.InputFormatException;
import ScoobyDoo.storage.Storage;
import ScoobyDoo.task.Event;
import ScoobyDoo.task.Task;
import ScoobyDoo.task.TaskList;

public class ByeCommand extends Command{
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        return ui.printByeMessage();
    }
}
