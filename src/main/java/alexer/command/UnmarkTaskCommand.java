package alexer.command;

import alexer.Alexer;
import alexer.task.TaskManager;
import alexer.ui.Response;

/**
 * A command to un-mark a task as done. Will do
 * nothing for tasks not marked as done.
 *
 * @author sayomaki
 */
public class UnmarkTaskCommand extends Command {
    public UnmarkTaskCommand() {
        super("unmark");
    }

    @Override
    public Response run(String... arguments) {
        int index = Integer.parseInt(arguments[0]); // assume valid integer provided here
        TaskManager taskManager = Alexer.getInstance().getTaskManager();

        // assume input here is valid, we will handle exceptions later
        taskManager.getTask(index - 1).unmarkDone();
        taskManager.saveTasks();

        StringBuilder builder = new StringBuilder("Alright, it seems you are not done with that yet.\n");
        builder.append("I have unmarked it for you.\n\n");
        builder.append(String.format("\t%s", taskManager.getTask(index - 1)));
        return new Response(builder.toString());
    }
}
