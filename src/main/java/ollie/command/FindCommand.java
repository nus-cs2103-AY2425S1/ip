package ollie.command;

import ollie.Response;
import ollie.Storage;
import ollie.TaskList;
import ollie.Ui;
import ollie.exception.OllieException;
import ollie.task.Task;

public class FindCommand extends Command{
    String findQuery;

    public FindCommand(String findQuery) {
        this.findQuery = findQuery;
    }
    @Override
    public Response execute(TaskList tasks, Ui ui, Storage storage) throws OllieException {
        return new Response(ui.getTaskListMessage(tasks.filterByString(this.findQuery)), false);
    }
}

