package command;
import fridayException.FridayException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws FridayException;
    public boolean isEndScanner() {
        return false;
    }
}
