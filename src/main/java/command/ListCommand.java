package command;

import exception.ExecuteCommandException;
import helper.Storage;
import helper.TaskList;
import helper.Ui;

public class ListCommand extends Command {
    public ListCommand() {}

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws ExecuteCommandException {
        // print task list idk how
        // TODO: use ui to print??
    }
}
