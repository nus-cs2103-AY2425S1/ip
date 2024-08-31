package commands;
import storage.Storage;
import storage.TaskList;
import ui.UI;

public interface Command {
    boolean execute(Storage storage, TaskList master, UI ui);
}
