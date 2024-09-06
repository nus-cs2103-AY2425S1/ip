package puke.commands;

import puke.TaskList;
import puke.exceptions.EmptyDescriptionException;
import puke.exceptions.MissingTimeException;
import puke.message.MessageBuilder;

/**
 * Command to add a new Deadline task.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Constructs a new AddDeadlineCommand with specified arguments.
     *
     * @param args the string containing the task description followed by '/by' and the deadline time.
     * @throws EmptyDescriptionException if the task description is empty.
     * @throws MissingTimeException if the deadline time is missing or empty.
     */
    public AddDeadlineCommand(String args) throws EmptyDescriptionException, MissingTimeException {
        if (args.isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }
        String[] parts = args.split(" /by ");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingTimeException();
        }
        this.description = parts[0].trim();
        this.by = parts[1].trim();
    }

    /**
     * Executes the add deadline command.
     * This method adds a new deadline task to the task list and sends a confirmation message
     * using the message builder.
     *
     * @param taskList the task list to which the deadline task will be added.
     * @param messageBuilder the message builder used to generate and send the confirmation message.
     * @return the confirmation message after adding the deadline task.
     */
    @Override
    public String execute(TaskList taskList, MessageBuilder messageBuilder) {
        return messageBuilder.sendMessage(taskList.addTask("deadline", description, by));
    }
}
