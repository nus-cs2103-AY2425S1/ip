package puke.commands;

import puke.exceptions.EmptyDescriptionException;
import puke.exceptions.MissingTimeException;
import puke.tasklist.TaskManager;
import puke.ui.MessageBuilder;

public class AddDeadlineCommand extends Command {
    private String description;
    private String by;

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

    @Override
    public void execute(TaskManager taskManager, MessageBuilder messageBuilder) {
        messageBuilder.sendMessage(taskManager.addTask("deadline", description, by));
    }
}
