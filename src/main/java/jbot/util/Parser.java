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

public class Parser {
    private Parser() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
    private static Map<String, JBotCommand> commandMap;
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

    public static JBotCommand parse(String userInput) throws InvalidCommandException {
        String inputCommand = userInput.split(" ")[0];
        JBotCommand command = commandMap.get(inputCommand);
        if (command == null) {
            throw new InvalidCommandException("Invalid command: " + inputCommand);
        }
        return command ;

    }

}
