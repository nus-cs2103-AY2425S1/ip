package botty.commands;

import botty.tasks.Task;
import botty.tasks.TaskManager;

public abstract class AddTaskCommand extends Command {
    protected String addToTaskList(TaskManager taskManager, Task task) {
        taskManager.addTask(task);

        return "I have added the following task to the list!\n"
                + task + "\n"
                + "You now have " + taskManager.size() + " tasks.";

    }
}
