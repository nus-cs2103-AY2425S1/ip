package controllers.commands;

import models.Task;
import models.TaskList;

/**
 * Represents a command to add a {@code Deadline} task to the task management system.
 * The {@code AddDeadlineCommand} class implements the {@code Command} interface and
 * adds a new {@code Deadline} task to the {@code TaskList}.
 *
 * <p>This command adds the deadline task to the list and prints a confirmation message.</p>
 */
public class AddDeadlineCommand implements Command {
    private Task task;

    /**
     * Constructs an {@code AddDeadlineCommand} with the specified {@code Deadline} task.
     *
     * @param task The {@code Deadline} task to be added.
     */
    public AddDeadlineCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add the {@code Deadline} task to the {@code TaskList}.
     * It adds the task and prints a confirmation message with the task's description.
     *
     * @param taskList The {@code TaskList} to which the deadline task is added.
     */
    @Override
    public void execute(TaskList taskList) {
        taskList.addTask(this.task);
        System.out.println("____________________________________________________________\n" +
                "added: " + this.task.getDescription() + "\n" +
                "____________________________________________________________");
    }
}
