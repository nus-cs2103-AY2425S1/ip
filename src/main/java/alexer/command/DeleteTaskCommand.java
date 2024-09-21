package alexer.command;

import alexer.Alexer;
import alexer.task.Task;
import alexer.task.TaskManager;
import alexer.ui.Response;

import static alexer.Prompter.MESSAGE_DELETE_TASK;

/**
 * A command to delete an existing task by the
 * ordered position of the task as indicated
 * by the list task command.
 *
 * @author sayomaki
 */
public class DeleteTaskCommand extends Command {

    public DeleteTaskCommand() {
        super("delete");
    }

    @Override
    public Response run(String... arguments) {
        int index = Integer.parseInt(arguments[0]) - 1;
        TaskManager taskManager = Alexer.getInstance().getTaskManager();
        Task task = taskManager.removeTask(index);
        taskManager.saveTasks();

        return new Response(String.format("%s\n\n\t%s\n\nYou have %d tasks remaining.",
                MESSAGE_DELETE_TASK, task, taskManager.getTaskCount()));
    }
}
