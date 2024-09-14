package alexer.command;

import alexer.Alexer;
import alexer.Prompter;
import alexer.task.Task;
import alexer.task.TaskManager;

import java.util.Arrays;
import java.util.List;

public class FindTaskCommand extends Command {
    public FindTaskCommand() {
        super("find");
    }

    @Override
    public void run(String[] arguments) {
        if (arguments.length == 0) {
            return;
        }

        String keyword = arguments[0];
        TaskManager taskManager = Alexer.getInstance().getTaskManager();
        List<Task> tasks = taskManager.findTask(keyword);

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append(String.format("\t%d: %s\n", i, tasks.get(i).toString()));
        }

        Prompter prompter = Alexer.getInstance().getPrompter();
        prompter.buildFilteredTaskList(output.toString()).printToConsole();
    }
}
