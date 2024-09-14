package yappingbot.commands;

import java.util.HashMap;

import yappingbot.exceptions.YappingBotInvalidTaskNumberException;
import yappingbot.exceptions.YappingBotOobException;
import yappingbot.exceptions.YappingBotUnknownCommandException;
import yappingbot.stringconstants.ReplyTextMessages;

/**
 * Parser class to aid in parsing command text and arguments.
 */
public class Parser {

    /**
     * Valid commands that are implemented.
     *
     * @see <a href="https://github.com/nus-cs2103-AY2425S1/forum/issues/22#issuecomment-2309939016"> Uses for enum </a>
     */
    public enum CommandTypes {
        FIND, LIST, MARK, UNMARK, DELETE, TODO, EVENT, DEADLINE, EXIT, RESET_LIST, UNKNOWN
    }

    /**
     * HashMap to map text (in any locale) to the respective CommandTypes.
     *
     * @see <a href="https://github.com/nus-cs2103-AY2425S1/forum/issues/22#issuecomment-2309939016"> Uses for enum </a>
     */
    public final HashMap<String, CommandTypes> commandsHashMap;


    /**
     * Constructs a Parser object.
     * This registers all possible user-inputted commands to the command hashmap.
     */
    public Parser() {
        commandsHashMap = new HashMap<>();
        commandsHashMap.put("find", CommandTypes.FIND);
        commandsHashMap.put("/", CommandTypes.FIND);

        commandsHashMap.put("reset", CommandTypes.RESET_LIST);

        commandsHashMap.put("list", CommandTypes.LIST);
        commandsHashMap.put("print", CommandTypes.LIST);
        commandsHashMap.put("p", CommandTypes.LIST);

        commandsHashMap.put("mark", CommandTypes.MARK);
        commandsHashMap.put("m", CommandTypes.MARK);
        commandsHashMap.put("unmark", CommandTypes.UNMARK);
        commandsHashMap.put("u", CommandTypes.UNMARK);

        commandsHashMap.put("delete", CommandTypes.DELETE);
        commandsHashMap.put("d", CommandTypes.DELETE);

        commandsHashMap.put("todo", CommandTypes.TODO);
        commandsHashMap.put("t", CommandTypes.TODO);
        commandsHashMap.put("event", CommandTypes.EVENT);
        commandsHashMap.put("e", CommandTypes.EVENT);
        commandsHashMap.put("deadline", CommandTypes.DEADLINE);
        commandsHashMap.put("dl", CommandTypes.DEADLINE);

        commandsHashMap.put("bye", CommandTypes.EXIT);
        commandsHashMap.put("exit", CommandTypes.EXIT);
        commandsHashMap.put(":q", CommandTypes.EXIT);

    }

    /**
     * Parses a given user-input command string, and returns the respective CommandTypes.
     *
     * @param commandString String of the user input or first word of user input that denotes
     *                      command desired.
     * @return CommandTypes of the corresponding command.
     * @throws YappingBotUnknownCommandException Exception if command is unimplemented.
     */
    public CommandTypes parseCommand(String commandString)
            throws YappingBotUnknownCommandException {
        assert commandString != null;
        assert commandsHashMap != null;
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

    /**
     * Parses any text that must be interpreted as an integer.
     *
     * @param userInputSlices String of text to be converted to integer.
     * @return integer of the parsed integer.
     * @throws YappingBotInvalidTaskNumberException Exception if given String is not a valid
     *         integer.
     */
    public static int parseTaskNumberSelected(String userInputSlices)
            throws YappingBotInvalidTaskNumberException {
        assert userInputSlices != null;
        int i;
        try {
            i = Integer.parseInt(userInputSlices) - 1;
        } catch (NumberFormatException ex) {
            throw new YappingBotInvalidTaskNumberException(userInputSlices);
        }
        return i;
    }
}
