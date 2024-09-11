package ollie.command;

import ollie.Response;
import ollie.Storage;
import ollie.TaskList;
import ollie.Ui;
import ollie.exception.OllieException;
import ollie.task.Task;

public class FindCommand extends Command {
    String filterParameter;

    public FindCommand(String filterParameter) {
        this.filterParameter = filterParameter;
    }

    @Override
    public Response execute(TaskList tasks, Ui ui, Storage storage) throws OllieException {
        assert(filterParameter != null);
        return new Response(ui.getTaskListMessage(tasks.filterByString(this.filterParameter)), false);
    }
}

