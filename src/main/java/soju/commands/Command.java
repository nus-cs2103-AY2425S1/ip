package soju.commands;
import soju.SojuException;
import soju.Storage;
import soju.TaskList;
import soju.Ui;

//import soju.TaskList;
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SojuException;
}
