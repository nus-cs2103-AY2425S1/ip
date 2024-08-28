package stan.commands;
import stan.TaskList;
import stan.Ui;
import stan.Storage;
import stan.StanException;
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws StanException;

    public boolean isExit() {
        return false;
    }
}
