package optimus.commands;

import optimus.Storage;
import optimus.TaskList;
import optimus.Ui;
import optimus.exceptions.OptimusExceptions;

public abstract class Command {

    abstract public void execute(Storage storage, TaskList tasks, Ui ui) throws OptimusExceptions;

    /**
     *
     * @return
     */
    public boolean shouldContinue() {
       return true;
    }
}
