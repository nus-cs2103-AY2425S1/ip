package puke.handlers;

import puke.commands.*;
import puke.exceptions.EmptyInputException;
import puke.exceptions.PukeException;
import puke.exceptions.UnknownCommandException;
import puke.tasklist.TaskManager;
import puke.tasks.Task;
import puke.ui.MessageBuilder;

import java.util.ArrayList;

public class InputManager {
    private TaskManager taskManager;
    private MessageBuilder messageBuilder;

    public InputManager(TaskManager taskManager, MessageBuilder messageBuilder) {
        this.taskManager = taskManager;
        this.messageBuilder = messageBuilder;
    }

    public void handleInput(String input) throws PukeException {
        input = input.trim();
        if (input.isEmpty()) {
            messageBuilder.sendMessage(new EmptyInputException().getMessage());
            throw new EmptyInputException();
        }

        String command = input.split("\\s+")[0];
        String args = input.substring(command.length()).trim();

        try {
            Command commandObject = parseCommand(command, args);
            commandObject.execute(taskManager, messageBuilder);
        } catch (PukeException e) {
            messageBuilder.sendMessage(e.getMessage());
            throw e;
        }
    }

    private Command parseCommand(String command, String args) throws PukeException {
        switch (command.toLowerCase()) {
            case "todo":
                return new AddTodoCommand(args);
            case "deadline":
                return new AddDeadlineCommand(args);
            case "event":
                return new AddEventCommand(args);
            case "delete":
                return new DeleteTaskCommand(args);
            case "mark":
                return new MarkTaskCommand(args, true);
            case "unmark":
                return new MarkTaskCommand(args, false);
            case "list":
                return new ListTasksCommand();
            case "find":
                return new FindTaskCommand(args);
            default:
                throw new UnknownCommandException();
        }
    }
}
