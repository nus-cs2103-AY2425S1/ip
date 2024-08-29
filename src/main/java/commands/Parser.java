package commands;

import exceptions.YappingBotUnknownCommandException;

import java.util.HashMap;

public class Parser {
    public static HashMap<String, CommandTypes> commandsHashMap;
    public Parser() {
        commandsHashMap = new HashMap<>();
        commandsHashMap.put("list", CommandTypes.LIST);
        commandsHashMap.put("mark", CommandTypes.MARK);
        commandsHashMap.put("unmark", CommandTypes.UNMARK);
        commandsHashMap.put("delete", CommandTypes.DELETE);
        commandsHashMap.put("todo", CommandTypes.TODO);
        commandsHashMap.put("event", CommandTypes.EVENT);
        commandsHashMap.put("deadline", CommandTypes.DEADLINE);
        commandsHashMap.put("bye", CommandTypes.EXIT);
    }
    public CommandTypes parseCommand(String commandString) throws YappingBotUnknownCommandException {
        if (commandString.toLowerCase().trim().isEmpty()) {
            throw new YappingBotUnknownCommandException();
        } else {
            if (commandsHashMap.containsKey(commandString)) {
                return commandsHashMap.get(commandString);
            } else {
                throw new YappingBotUnknownCommandException(commandString);
            }
        }
    }
}
