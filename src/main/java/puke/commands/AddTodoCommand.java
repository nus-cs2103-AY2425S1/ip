package puke.commands;

import puke.exceptions.EmptyDescriptionException;
import puke.tasklist.TaskManager;
import puke.ui.MessageBuilder;

public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskManager taskManager, MessageBuilder messageBuilder) throws EmptyDescriptionException {
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("tasks.Todo description cannot be empty.");
        }
        messageBuilder.sendMessage(taskManager.addTask("todo", description));
    }
}
