package yappingbot.commands;

import yappingbot.exceptions.YappingBotInvalidTaskNumberException;
import yappingbot.exceptions.YappingBotUnknownCommandException;
import yappingbot.tasks.tasklist.TaskList;

import java.util.HashMap;

public class Parser {
    // https://github.com/nus-cs2103-AY2425S1/forum/issues/22#issuecomment-2309939016
    public final HashMap<String, CommandTypes> commandsHashMap;
    public Parser() {
        commandsHashMap = new HashMap<>();
        commandsHashMap.put("find", CommandTypes.FIND);
        commandsHashMap.put("reset", CommandTypes.RESET_LIST);
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
    public static int parseTaskNumberSelected(String userInputSlice, TaskList userList) throws YappingBotInvalidTaskNumberException {
        int i;
        try {
            i = Integer.parseInt(userInputSlice) - 1;
        } catch (NumberFormatException ex) {
            throw new YappingBotInvalidTaskNumberException(userInputSlice);
        }
        return i;
    }
}
