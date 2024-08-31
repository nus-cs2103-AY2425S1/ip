package botty.commands;

import botty.tasks.Task;
import botty.tasks.TaskManager;

/**
 * Defines the behaviour of commands that add task
 */
public abstract class AddTaskCommand extends Command {
    /**
     * Adds the given task to the task manager
     * @param taskManager
     * @param task
     * @return the success message
     */
    protected String addToTaskList(TaskManager taskManager, Task task) {
        taskManager.addTask(task);

        return "I have added the following task to the list!\n"
                + task + "\n"
                + "You now have " + taskManager.size() + " tasks.";

    }
}
