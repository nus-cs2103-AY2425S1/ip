package papadom.commands;
import papadom.Exceptions.NoTaskException;
import papadom.Storage.*;
import papadom.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws NoTaskException;
    public abstract boolean isExit();
}
