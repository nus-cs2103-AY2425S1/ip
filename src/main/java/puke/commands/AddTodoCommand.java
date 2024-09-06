package puke.commands;

import puke.TaskList;
import puke.exceptions.EmptyDescriptionException;
import puke.message.MessageBuilder;

/**
 * Command to add a new Todo task.
 */
public class AddTodoCommand extends Command {
    private String description;

    /**
     * Constructs an AddTodoCommand with the specified description.
     *
     * @param description the description of the Todo task to be added.
     * @throws EmptyDescriptionException if the description is empty.
     */
    public AddTodoCommand(String description) throws EmptyDescriptionException {
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("Todo description cannot be empty.");
        }
        this.description = description;
    }

    /**
     * Executes the command to add a Todo task to the TaskList and sends a confirmation message.
     *
     * @param taskList the TaskList to which the Todo task will be added.
     * @param messageBuilder the MessageBuilder used to construct and send the confirmation message.
     * @return the confirmation message after adding the Todo task.
     * @throws EmptyDescriptionException if the description is empty.
     */
    @Override
    public String execute(TaskList taskList, MessageBuilder messageBuilder) throws EmptyDescriptionException {
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("Todo description cannot be empty.");
        }
        return messageBuilder.sendMessage(taskList.addTask("todo", description));
    }
}
