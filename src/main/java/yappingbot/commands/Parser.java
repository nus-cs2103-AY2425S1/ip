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
     * @param userInputSlice String of text to be converted to integer.
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

    /**
     * Asserts if the number of slices in the String array given <b>at least</b> the minimum
     * number of arguments required according to the interger i.
     *
     * @param userInputSlices String array of input command, including the command verb and its
     *                        arguments
     * @param i integer of how many arguements are required for this command.
     * @throws YappingBotOobException Exception if there is not enough arguments.
     */
    public static void checkMinimumArgsAvailable(String[] userInputSlices, int i)
    throws YappingBotOobException {
        assert userInputSlices != null;
        if ((userInputSlices.length - 1) < i) {
            throw new YappingBotOobException(ReplyTextMessages.NOT_ENOUGH_ARGUMENTS, i);
        }
    }
}
