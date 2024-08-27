package Commands;
import Main.TaskList;
import UI.Ui;
import Storage.Storage;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
