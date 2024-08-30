package puke.commands;

import puke.exceptions.EmptyDescriptionException;
import puke.exceptions.MissingEventTimeException;
import puke.tasklist.TaskManager;
import puke.ui.MessageBuilder;

/**
 * Command to add a new Event task.
 */
public class AddEventCommand extends Command {
    private String description;
    private String from;
    private String to;

    /**
     * Constructs a new AddEventCommand with specified arguments.
     *
     * @param args the string containing the task description followed by '/from' for start time and '/to' for end time.
     * @throws EmptyDescriptionException if the task description is empty.
     * @throws MissingEventTimeException if the start or end time are missing or empty.
     */
    public AddEventCommand(String args) throws EmptyDescriptionException, MissingEventTimeException {
        if (args.isEmpty()) {
            throw new EmptyDescriptionException("event");
        }
        String[] parts = args.split(" /from | /to ");
        if (parts.length < 3 || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            throw new MissingEventTimeException();
        }
        this.description = parts[0].trim();
        this.from = parts[1].trim();
        this.to = parts[2].trim();
    }

    /**
     * Executes the add event command.
     * This method adds a new event task to the task manager with a specified duration and sends a confirmation message using the message builder.
     *
     * @param taskManager the task manager to add the task to.
     * @param messageBuilder the message builder to send the confirmation message.
     */
    @Override
    public void execute(TaskManager taskManager, MessageBuilder messageBuilder) {
        messageBuilder.sendMessage(taskManager.addTask("event", description, from, to));
    }
}
