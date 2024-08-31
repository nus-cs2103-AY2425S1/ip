package command;

import java.util.List;

import tasklist.TaskList;
import tasks.Task;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /** An optional task field that is not used in this command. */
    protected Task task;

    /**
     * Executes the command by listing all tasks in the task list and
     * displaying them in the command line interface.
     *
     * @param tasklist The TaskList containing the tasks to be listed.
     * @return List of tasks.
     */
    @Override
    public String execute(TaskList tasklist) {
        List<Task> tasks = tasklist.getTasks();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + ". " + tasks.get(i) + "\n");
        }

        return sb.toString();
    }

    /**
     * Indicates whether this command causes the application to exit.
     *
     * @return false, as this command does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
