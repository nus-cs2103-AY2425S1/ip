package commands;
import storage.Storage;
import storage.TaskList;
import ui.UI;

public class byeCommand implements Command {
    public boolean execute(Storage storage, TaskList master, UI ui) {
        return true;
    }
}
