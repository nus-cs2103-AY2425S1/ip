package jbot.util;

import java.util.HashMap;
import java.util.Map;

import jbot.command.ByeCommand;
import jbot.command.DeadlineCommand;
import jbot.command.DeleteCommand;
import jbot.command.EventCommand;
import jbot.command.FindCommand;
import jbot.command.HelpCommand;
import jbot.command.InvalidCommandException;
import jbot.command.JBotCommand;
import jbot.command.ListCommand;
import jbot.command.MarkCommand;
import jbot.command.ToDoCommand;
import jbot.command.UnmarkCommand;

/**
 * A utility class for parsing user input into commands. This class cannot be instantiated.
 */
public class Parser {
    private Parser() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    private static Map<String, JBotCommand> commandMap;

    /**
     * Initializes the command map with the available commands.
     * This method should be called before attempting to parse user input.
     */
    public static void init() {
        Parser.commandMap = new HashMap<>();

        // Assert that commands are not initialized yet
        assert Parser.commandMap.isEmpty() : "Command map should be empty before initialization";

        Parser.commandMap.put("list", ListCommand.getInstance());
        Parser.commandMap.put("bye", ByeCommand.getInstance());
        Parser.commandMap.put("mark", MarkCommand.getInstance());
        Parser.commandMap.put("unmark", UnmarkCommand.getInstance());
        Parser.commandMap.put("todo", ToDoCommand.getInstance());
        Parser.commandMap.put("deadline", DeadlineCommand.getInstance());
        Parser.commandMap.put("event", EventCommand.getInstance());
        Parser.commandMap.put("delete", DeleteCommand.getInstance());
        Parser.commandMap.put("find", FindCommand.getInstance());
        Parser.commandMap.put("help", HelpCommand.getInstance());

        // Assert that all expected commands are present in the map
        assert Parser.commandMap.size() == 10 : "Command map size should be 9 after initialization";
        assert Parser.commandMap.containsKey("list") : "Command map should contain 'list' command";
        assert Parser.commandMap.containsKey("bye") : "Command map should contain 'bye' command";
        assert Parser.commandMap.containsKey("mark") : "Command map should contain 'mark' command";
        assert Parser.commandMap.containsKey("unmark") : "Command map should contain 'unmark' command";
        assert Parser.commandMap.containsKey("todo") : "Command map should contain 'todo' command";
        assert Parser.commandMap.containsKey("deadline") : "Command map should contain 'deadline' command";
        assert Parser.commandMap.containsKey("event") : "Command map should contain 'event' command";
        assert Parser.commandMap.containsKey("delete") : "Command map should contain 'delete' command";
        assert Parser.commandMap.containsKey("find") : "Command map should contain 'find' command";
    }

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param userInput The input from the user, typically a command string.
     * @return The JBotCommand corresponding to the user input.
     * @throws InvalidCommandException If the user input does not match any known command.
     */
    public static JBotCommand parse(String userInput) throws InvalidCommandException {

        String inputCommand = userInput.split(" ")[0];

        JBotCommand command = Parser.commandMap.get(inputCommand);

        if (command == null) {
            throw new InvalidCommandException("Invalid command: " + inputCommand);
        }

        // Assert that the returned command is not null
        assert command != null : "Returned command should not be null";

        return command;
    }
}