package alexer.command;

import alexer.Alexer;
import alexer.task.Task;
import alexer.task.TaskManager;
import alexer.ui.Response;

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

        String response = "Don't want to see that task anymore? I got you!\n" +
                String.format("\t%s\n", task) +
                String.format("\nYou have %d tasks remaining.\n", taskManager.getTaskCount());

        return new Response(response);
    }
}
