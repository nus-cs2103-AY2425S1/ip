package alexer.command;

import alexer.Alexer;
import alexer.Prompter;
import alexer.task.Task;
import alexer.task.TaskManager;
import alexer.ui.Response;

import java.util.List;

/**
 * A command to find tasks that has the description
 * containing the search word term. Can return more
 * than one task, and the output is shown as a list.
 *
 * @author sayomaki
 */
public class FindTaskCommand extends Command {
    public FindTaskCommand() {
        super("find");
    }

    @Override
    public Response run(String... arguments) {
        if (arguments.length == 0) {
            return new Response("Oops, did you forget to tell me what tasks to find for?",
                    Response.ResponseType.ERROR);
        }

        String keyword = arguments[0];

        TaskManager taskManager = Alexer.getInstance().getTaskManager();
        assert taskManager != null; // ensure that we definitely have a task manager

        List<Task> tasks = taskManager.findTask(keyword);
        if (tasks.isEmpty()) {
            return new Response("Hmm, I don't think I can find anything as you don't have any tasks!\n" +
                    "Why not start by adding one first?");
        }

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append(String.format("\t%d: %s\n", i, tasks.get(i).toString()));
        }

        return Prompter.buildFilteredTaskList(output.toString());
    }
}
