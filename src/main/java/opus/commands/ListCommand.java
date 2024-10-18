package opus.commands;

import opus.Storage;
import opus.TaskList;
import opus.tasks.Task;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand implements Command {
    /**
     * Executes the command to list all tasks in the task list.
     *
     * @param taskList The task list.
     * @param storage The storage.
     * @return The response to the user.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            response.append(i + 1).append(". ").append(task.toString()).append("\n");
        }
        return response.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
