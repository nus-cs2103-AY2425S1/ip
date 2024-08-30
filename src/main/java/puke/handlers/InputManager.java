package puke.handlers;

import puke.commands.AddDeadlineCommand;
import puke.commands.AddEventCommand;
import puke.commands.AddTodoCommand;
import puke.commands.Command;
import puke.commands.DeleteTaskCommand;
import puke.commands.FindTaskCommand;
import puke.commands.ListTasksCommand;
import puke.commands.MarkTaskCommand;
import puke.exceptions.EmptyInputException;
import puke.exceptions.PukeException;
import puke.exceptions.UnknownCommandException;
import puke.tasklist.TaskManager;
import puke.ui.MessageBuilder;

/**
 * Manages user input by parsing and executing commands.
 */
public class InputManager {
    private TaskManager taskManager;
    private MessageBuilder messageBuilder;

    /**
     * Constructs an InputManager with the specified TaskManager and MessageBuilder.
     *
     * @param taskManager the TaskManager to handle task operations
     * @param messageBuilder the MessageBuilder to construct and send messages
     */
    public InputManager(TaskManager taskManager, MessageBuilder messageBuilder) {
        this.taskManager = taskManager;
        this.messageBuilder = messageBuilder;
    }

    /**
     * Handles the input by parsing the command and its arguments, and executing the corresponding command.
     *
     * @param input the user input to be processed
     * @throws PukeException if an error occurs during command execution
     */
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

    /**
     * Parses the command string and its arguments to create the corresponding Command object.
     *
     * @param command the command string to be parsed
     * @param args the arguments associated with the command
     * @return the Command object corresponding to the parsed command
     * @throws PukeException if the command is unknown or invalid
     */
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
