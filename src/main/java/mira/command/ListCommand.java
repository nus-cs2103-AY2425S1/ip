package mira.command;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the command by listing all tasks in the task list.
     *
     * @return A string representation of all tasks in the task list.
     */
    @Override
    public String execute() {
        return taskList.listTasks();
    }
}
