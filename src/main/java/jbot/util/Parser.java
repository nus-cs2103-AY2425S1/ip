package jbot.util;

import java.util.HashMap;
import java.util.Map;

import jbot.command.ByeCommand;
import jbot.command.DeadlineCommand;
import jbot.command.DeleteCommand;
import jbot.command.EventCommand;
import jbot.command.FindCommand;
import jbot.command.InvalidCommandException;
import jbot.command.JBotCommand;
import jbot.command.ListCommand;
import jbot.command.MarkCommand;
import jbot.command.ToDoCommand;
import jbot.command.UnmarkCommand;


/**
 * A utility class for parsing user input into commands. This class cannot be instantiated.
 */
@SuppressWarnings({"StaticVariableMayNotBeInitialized", "StaticVariableUsedBeforeInitialization"})

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
        Parser.commandMap.put("list", ListCommand.getInstance());
        Parser.commandMap.put("bye", ByeCommand.getInstance());
        Parser.commandMap.put("mark", MarkCommand.getInstance());
        Parser.commandMap.put("unmark", UnmarkCommand.getInstance());
        Parser.commandMap.put("todo", ToDoCommand.getInstance());
        Parser.commandMap.put("deadline", DeadlineCommand.getInstance());
        Parser.commandMap.put("event", EventCommand.getInstance());
        Parser.commandMap.put("delete", DeleteCommand.getInstance());
        Parser.commandMap.put("find", FindCommand.getInstance());

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
        return command;
    }
}