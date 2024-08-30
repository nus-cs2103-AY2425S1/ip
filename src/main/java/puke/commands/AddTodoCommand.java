package puke.commands;

import puke.exceptions.EmptyDescriptionException;
import puke.tasklist.TaskManager;
import puke.ui.MessageBuilder;

/**
 * Command to add a new Todo task.
 */
public class AddTodoCommand extends Command {
    private String description;

    /**
     * Constructs an AddTodoCommand with the specified description.
     *
     * @param description the description of the Todo task to be added
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add a Todo task to the TaskManager and sends a confirmation message.
     *
     * @param taskManager the TaskManager to which the Todo task will be added
     * @param messageBuilder the MessageBuilder used to construct and send a confirmation message
     * @throws EmptyDescriptionException if the description is empty
     */
    @Override
    public void execute(TaskManager taskManager, MessageBuilder messageBuilder) throws EmptyDescriptionException {
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("tasks.Todo description cannot be empty.");
        }
        messageBuilder.sendMessage(taskManager.addTask("todo", description));
    }
}
