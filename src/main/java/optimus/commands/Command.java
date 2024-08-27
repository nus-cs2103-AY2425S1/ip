package optimus.commands;

import optimus.Storage;
import optimus.TaskList;
import optimus.Ui;
import optimus.commands.Command;
import optimus.exceptions.OptimusExceptions;
import optimus.tasks.Task;

public abstract class Command {

    abstract public void execute(Storage storage, TaskList tasks, Ui ui) throws OptimusExceptions;
    public boolean shouldContinue() {
       return true;
    }
}
