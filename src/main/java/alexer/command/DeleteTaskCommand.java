package alexer.command;

import alexer.Alexer;
import alexer.task.Task;
import alexer.task.TaskManager;
import alexer.ui.Response;

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

        String response = "Don't want to see that task anymore? I got you!\n\n" +
                String.format("\t%s\n\nYou have %d tasks remaining.", task, taskManager.getTaskCount());

        return new Response(response);
    }
}
