package controllers.commands;

import models.Task;
import models.Todo;
import models.TaskList;

/**
 * Represents a command to add a {@code Todo} task to the task management system.
 * The {@code AddTodoCommand} class implements the {@code Command} interface and
 * adds a new {@code Todo} task to the {@code TaskList}.
 *
 * <p>This command adds a task to the list and prints a confirmation message.</p>
 */
public class AddTodoCommand implements Command {
    private Todo task;

    /**
     * Constructs an {@code AddTodoCommand} with the specified {@code Todo} task.
     *
     * @param task The {@code Todo} task to be added.
     */
    public AddTodoCommand(Todo task) {
        this.task = task;
    }

    /**
     * Executes the command to add the {@code Todo} task to the {@code TaskList}.
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
