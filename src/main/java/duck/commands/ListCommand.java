package duck.commands;

import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.data.task.Task;
import duck.storage.Storage;
import duck.ui.Ui;

public class ListCommand extends Command {

    public ListCommand(String message) {
        super(message);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DuckException {
        int idx = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task t : tasks) {
            System.out.println(idx++ + "." + t.toString());
        }
        System.out.println();
    }

    @Override
    public boolean isExit() {
        return false;
    }


}
