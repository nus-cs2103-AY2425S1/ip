package puke;

import puke.commands.AddDeadlineCommand;
import puke.commands.AddEventCommand;
import puke.commands.AddTodoCommand;
import puke.commands.Command;
import puke.commands.DeleteTaskCommand;
import puke.commands.FindTaskCommand;
import puke.commands.ListTasksCommand;
import puke.commands.MarkTaskCommand;
import puke.commands.UpdateTaskCommand;
import puke.exceptions.EmptyInputException;
import puke.exceptions.PukeException;
import puke.exceptions.UnknownCommandException;
import puke.message.MessageBuilder;

/**
 * Parses user input and executes corresponding commands.
 * The Parser class is responsible for handling user input, interpreting commands,
 * and delegating the execution of these commands to the appropriate Command objects.
 */
public class Parser {
    private TaskList taskList;
    private MessageBuilder messageBuilder;

    /**
     * Constructs a Parser with the specified TaskList and MessageBuilder.
     *
     * @param taskList the TaskList to handle task operations
     * @param messageBuilder the MessageBuilder to construct and send messages
     */
    public Parser(TaskList taskList, MessageBuilder messageBuilder) {
        assert taskList != null : "TaskList should not be null";
        assert messageBuilder != null : "MessageBuilder should not be null";

        this.taskList = taskList;
        this.messageBuilder = messageBuilder;
    }

    /**
     * Processes the user input by parsing the command and its arguments, then executes the corresponding command.
     *
     * @param input the user input to be processed
     * @return a message resulting from the execution of the command
     */
    public String handleInput(String input) {
        assert input != null : "Input should not be null";

        input = input.trim();
        if (input.isEmpty()) {
            return messageBuilder.sendMessage(new EmptyInputException().getMessage());
        }

        String commandType = input.split("\\s+")[0];
        String args = input.substring(commandType.length()).trim();

        try {
            Command commandObject = parseCommand(commandType, args);
            return commandObject.execute(taskList, messageBuilder);
        } catch (PukeException e) {
            return messageBuilder.sendMessage(e.getMessage());
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
        assert command != null : "Command should not be null";
        assert args != null : "Args should not be null";

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
        case "update":
            return new UpdateTaskCommand(args);
        default:
            throw new UnknownCommandException();
        }
    }
}
