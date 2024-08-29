package sage.Command;

import sage.List.TaskList;
import sage.Storage;
import sage.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks("Here are the tasks in your list:");
    }
}
