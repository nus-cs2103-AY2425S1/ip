package alexer.command;

import alexer.Alexer;
import alexer.task.Task;
import alexer.task.TaskManager;
import alexer.ui.Response;

import static alexer.Prompter.MESSAGE_UNMARK_TASK;

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
        try {
            int index = Integer.parseInt(arguments[0]);
            TaskManager taskManager = Alexer.getInstance().getTaskManager();

            Task task = taskManager.getTask(index - 1);
            if (task == null) {
                return new Response("Error: Task to un-mark not found!", Response.ResponseType.ERROR);
            }

            task.unmarkDone();
            taskManager.saveTasks();

            return new Response(String.format("%s\n\n\t%s",
                    MESSAGE_UNMARK_TASK, taskManager.getTask(index - 1)));
        } catch (NumberFormatException e) {
            return new Response("Error: That does not seem to be a valid task index, please try again!",
                    Response.ResponseType.ERROR);
        }
    }
}
