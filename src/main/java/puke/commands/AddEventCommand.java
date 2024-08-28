package puke.commands;

import puke.exceptions.EmptyDescriptionException;
import puke.exceptions.MissingEventTimeException;
import puke.tasklist.TaskManager;
import puke.ui.MessageBuilder;

public class AddEventCommand extends Command {
    private String description;
    private String from;
    private String to;

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

    @Override
    public void execute(TaskManager taskManager, MessageBuilder messageBuilder) {
        messageBuilder.sendMessage(taskManager.addTask("event", description, from, to));
    }
}
