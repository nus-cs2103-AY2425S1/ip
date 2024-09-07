package controllers.commands;

import models.Event;
import models.TaskList;

/**
 * Represents a command to add an {@code Event} task to the task management system.
 * The {@code AddEventCommand} class implements the {@code Command} interface and
 * adds a new {@code Event} task to the {@code TaskList}.
 *
 * <p>This command adds the event task to the list and prints a confirmation message.</p>
 */
public class AddEventCommand implements Command {
    private Event task;

    /**
     * Constructs an {@code AddEventCommand} with the specified {@code Event} task.
     *
     * @param task The {@code Event} task to be added.
     */
    public AddEventCommand(Event task) {
        this.task = task;
    }

    /**
     * Executes the command to add the {@code Event} task to the {@code TaskList}.
     * It adds the task and prints a confirmation message with the task's description.
     *
     * @param taskList The {@code TaskList} to which the event task is added.
     */
    @Override
    public void execute(TaskList taskList) {
        taskList.addTask(this.task);
        System.out.println("____________________________________________________________\n" +
                "added: " + this.task.getDescription() + "\n" +
                "____________________________________________________________");
    }
}
