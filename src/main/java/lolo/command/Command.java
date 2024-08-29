package lolo.command;
import lolo.Ui;
import lolo.LoloException;
import lolo.storage.Storage;
import lolo.task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws LoloException;
    public boolean isExit() {
        return false;
    }
}