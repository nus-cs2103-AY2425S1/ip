package controllers.commands;

import models.Task;
import models.TaskList;

/**
 * Represents a command to delete a task from the task management system.
 * The {@code DeleteTaskCommand} class implements the {@code Command} interface and
 * removes a task at the specified index from the {@code TaskList}.
 *
 * <p>This command removes the task and prints the description of the removed task.</p>
 */
public class DeleteTaskCommand implements Command {
    private int index;

    /**
     * Constructs a {@code DeleteTaskCommand} with the specified task index.
     *
     * @param index The index of the task to delete (1-based index).
     */
    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete the task at the specified index in the {@code TaskList}.
     * It removes the task and prints a confirmation message along with the task's description.
     *
     * @param taskList The {@code TaskList} from which the task is removed.
     */
    @Override
    public void execute(TaskList taskList) {
        Task task = taskList.removeTask(this.index);
        System.out.println("____________________________________________________________\n" +
                "Removed: " + task.getDescription() + "\n" +
                "____________________________________________________________");
    }
}
