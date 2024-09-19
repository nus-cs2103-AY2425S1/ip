package ScoobyDoo.Command;

import ScoobyDoo.UI.UI;
import ScoobyDoo.storage.Storage;
import ScoobyDoo.task.TaskList;
import javafx.application.Platform;

public class ByeCommand extends Command{
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        Platform.exit();
        return ui.printByeMessage();
    }
}
