package alexer.command;

import alexer.Alexer;
import alexer.Prompter;
import alexer.task.Task;
import alexer.task.TaskManager;
import alexer.ui.Response;

import java.util.List;

public class FindTaskCommand extends Command {
    public FindTaskCommand() {
        super("find");
    }

    @Override
    public Response run(String... arguments) {
        if (arguments.length == 0) {
            return null;
        }

        String keyword = arguments[0];

        TaskManager taskManager = Alexer.getInstance().getTaskManager();
        assert taskManager != null; // ensure that we definitely have a task manager

        List<Task> tasks = taskManager.findTask(keyword);

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append(String.format("\t%d: %s\n", i, tasks.get(i).toString()));
        }

        Prompter prompter = Alexer.getInstance().getPrompter();
        return prompter.buildFilteredTaskList(output.toString());
    }
}
