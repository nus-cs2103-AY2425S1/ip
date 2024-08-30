package soju.commands;
import soju.SojuException;
import soju.Storage;
import soju.TaskList;
import soju.Ui;

//import soju.TaskList;

/**
 * Command is an abstract class that has an execute function
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SojuException;
}
