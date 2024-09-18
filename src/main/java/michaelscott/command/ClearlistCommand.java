package michaelscott.command;

import michaelscott.task.TaskList;

/**
 * Class used to represent the functionality of a Clearlist Command
 * The command removes all the tasks from the list when executed
 */
public class ClearlistCommand implements Command {

    @Override
    public String execute(TaskList tasks) {
        tasks.clearList();
        return "You know what? Forget about it. It’s gone. Just like I forgot about Toby.";
    }

    @Override
    public String getSimpleName() {
        return "ClearlistCommand";
    }
}
