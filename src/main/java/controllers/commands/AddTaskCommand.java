package controllers.commands;

import models.Task;
import models.TaskList;

/**
 * Represents a command to add a generic {@code Task} to the task management system.
 * The {@code AddTaskCommand} class implements the {@code Command} interface and
 * adds a new {@code Task} to the {@code TaskList}.
 *
 * <p>This command adds a task to the list and prints a confirmation message.</p>
 */
public class AddTaskCommand implements Command {
    private Task task;

    /**
     * Constructs an {@code AddTaskCommand} with the specified {@code Task}.
     *
     * @param task The task to be added.
     */
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add the {@code Task} to the {@code TaskList}.
     * It adds the task and prints a confirmation message with the task's description.
     *
     * @param taskList The {@code TaskList} to which the task is added.
     */
    @Override
    public void execute(TaskList taskList) {
        taskList.addTask(this.task);
        System.out.println("____________________________________________________________\n" +
                "added: " + this.task.getDescription() + "\n" +
                "____________________________________________________________");
    }
}
