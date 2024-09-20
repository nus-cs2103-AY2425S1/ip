package alexer.command;

import alexer.Alexer;
import alexer.task.TaskManager;
import alexer.ui.Response;

/**
 * Command to mark tasks as done. Does nothing if
 * task is already marked as done.
 *
 * <p>
 * Usage: mark
 *      where n is the index of the task
 * Example: mark 3
 * </p>
 *
 * @author sayomaki
 */
public class MarkTaskCommand extends Command {
    public MarkTaskCommand() {
        super("mark");
    }

    @Override
    public Response run(String... arguments) {
        int index = Integer.parseInt(arguments[0]); // assume index is valid integer, will handle error later
        TaskManager taskManager = Alexer.getInstance().getTaskManager();

        // assume input here is valid, we will handle exceptions later
        taskManager.getTask(index - 1).markAsDone();
        taskManager.saveTasks();

        return new Response("Great job completing the task! Keep up the great work!\n\n" +
                String.format("\t%s", taskManager.getTask(index - 1)));
    }
}
