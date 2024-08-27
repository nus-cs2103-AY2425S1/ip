package optimus.commands;

import optimus.commands.Command;
import optimus.Storage;
import optimus.TaskList;
import optimus.Ui;
import optimus.exceptions.InvalidTaskNumberException;

public class ListCommand  extends Command {

    public ListCommand() {

    }
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws InvalidTaskNumberException {
        for (int i = 0; i < tasks.getNumOfTasks(); i++) {
            ui.printToInterface(String.format("%d. %s", i + 1, tasks.getTask(i)));
        }
    }
}
