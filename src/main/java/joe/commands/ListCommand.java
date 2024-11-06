package joe.commands;

import joe.tasks.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    private final TaskList taskList;

    /**
     * Constructs a ListCommand object.
     *
     * @param taskList The task list to list tasks from.
     */
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public String execute() {
        return taskList.listTasks();
    }
}
