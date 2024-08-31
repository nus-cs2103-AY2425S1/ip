package commands;

import tasks.TaskManager;
import tasks.Task;

public abstract class AddTaskCommand extends Command {
    protected String addToTaskList(TaskManager taskManager, Task task) {
        taskManager.addTask(task);

        return "I have added the following task to the list!\n" +
                task + "\n" +
                "You now have " + taskManager.size() + " tasks.";

    }
}
