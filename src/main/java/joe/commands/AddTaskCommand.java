package joe.commands;

import joe.tasks.Task;
import joe.tasks.TaskList;

/**
 * Represents a command to add a task to the task list.
 */
public class AddTaskCommand extends Command {
    private final TaskList taskList;
    private final Task task;

    /**
     * Constructs an AddTaskCommand object.
     *
     * @param taskList The task list to add the task to.
     * @param task     The task to be added.
     */
    public AddTaskCommand(TaskList taskList, Task task) {
        this.taskList = taskList;
        this.task = task;
    }

    @Override
    public String execute() {
        return taskList.addTask(task);
    }
}
