package joe;

import java.util.HashMap;

import joe.command.Command;
import joe.command.CommandFactory;

/**
 * The {@code Parser} class is responsible for parsing raw input from the user and
 * returning a {@code Command} object that can be executed. It interprets user input,
 * extracts command arguments, and creates a suitable {@code Command} object through the
 * {@link CommandFactory}.
 */
public class Parser {
    /** The message associated with the command, usually extracted from user input. */
    public final String message;

    /** Enum representing possible command arguments that can be parsed from the user input. */
    public enum CommandArgs { FROM, TO, BY }

    /** A map containing parsed argument names mapped to their corresponding values. */
    private HashMap<CommandArgs, String> parsedArgs;

    private final Commands commandCode;
    /**
     * Private constructor to create a {@code Parser} object with the parsed command code,
     * message, and arguments.
     *
     * @param commandCode The command code parsed from user input.
     * @param message The message extracted from user input.
     * @param parsedArgs A map of argument names and their corresponding values.
     */
    private Parser(Commands commandCode, String message, HashMap<CommandArgs, String> parsedArgs) {
        this.commandCode = commandCode;
        this.message = message;
        this.parsedArgs = parsedArgs;
    }

    /**
     * Retrieves the value of a parsed argument based on the provided {@link CommandArgs} key.
     *
     * @param commandArgs The {@link CommandArgs} key representing the argument to retrieve.
     * @return The value associated with the specified {@code commandArgs} key,
     *      or {@code null} if the key is not present.
     */
    public String getParsedArgs(CommandArgs commandArgs) {
        return parsedArgs.get(commandArgs);
    }

    /**
     * Parses the raw input from the user and returns a {@link Command} object.
     *
     * @param rawInput The raw string input provided by the user.
     * @return A {@link Command} object constructed based on the parsed input.
     * @throws JoeException if the input is empty or the command is unknown.
     */
    public static Command parse(String rawInput) {
        Parser parsedObj = Parser.parseInputToObject(rawInput);
        Command c = CommandFactory.getCommand(parsedObj.commandCode, parsedObj);
        return c;
    }

    /**
     * Parses the raw input string into a {@code Parser} object, extracting the command code,
     * message, and arguments.
     *
     * @param rawInput The raw string input provided by the user.
     * @return A {@code Parser} object containing parsed command code, message, and arguments.
     * @throws JoeException if the input is empty or if an unknown command is provided.
     */
    public static Parser parseInputToObject(String rawInput) {
        if (rawInput.isEmpty()) {
            throw new JoeException("OOPS! You did not enter anything");
        }

        Commands commandCode = parseCommandCode(rawInput);
        String message = parseMessage(rawInput);
        HashMap<CommandArgs, String> parsedArgs = parseArguments(rawInput);

        return new Parser(commandCode, message, parsedArgs);
    }

    /**
     * Extracts the command code from the raw input string.
     *
     * @param rawInput The raw string input provided by the user.
     * @return The parsed {@link Commands} enum value representing the command.
     * @throws JoeException if the command code is unknown.
     */
    private static Commands parseCommandCode(String rawInput) {
        String commandCodeString = extractUntilSpace(rawInput);
        try {
            return Commands.valueOf(commandCodeString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new JoeException("Unknown command");
        }
    }

    /**
     * Extracts the message part from the raw input string after the command code.
     *
     * @param rawInput The raw string input provided by the user.
     * @return The extracted message as a {@code String}.
     */
    private static String parseMessage(String rawInput) {
        if (!rawInput.contains(" ")) {
            return "";
        }
        int pointer = rawInput.indexOf(" ") + 1;
        StringBuilder message = new StringBuilder();
        for (; pointer < rawInput.length(); pointer++) {
            char curChar = rawInput.charAt(pointer);
            if (curChar == '/') { // Separator for arguments
                break;
            }
            message.append(curChar);
        }
        return message.toString().trim(); // Trim to remove trailing spaces
    }

    /**
     * Parses the arguments from the raw input string. Arguments are expected to be prefixed
     * by a slash (e.g., "/from", "/to") and followed by their corresponding values.
     *
     * @param argumentString The raw string input provided by the user.
     * @return A {@link HashMap} mapping each {@link CommandArgs} to its value.
     * @throws JoeException if an invalid argument format is provided.
     */
    private static HashMap<CommandArgs, String> parseArguments(String argumentString) {
        if (!argumentString.contains("/")) {
            // no arguments in the string
            return new HashMap<>();
        }
        HashMap<CommandArgs, String> parsedArgs = new HashMap<>();
        String[] optsArr = argumentString.split(" ");
        for (int i = 0; i < optsArr.length; i++) {
            String curStr = optsArr[i];
            if (curStr.startsWith("/")) {
                try {
                    CommandArgs commandArgs = CommandArgs.valueOf(curStr.substring(1).toUpperCase());
                    parsedArgs.put(commandArgs, optsArr[i + 1]);
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                    throw new JoeException("Please provide a valid command");
                }
            }
        }
        return parsedArgs;
    }

    /**
     * Extracts the substring from the input string until the first space character is encountered.
     *
     * @param input The raw input string.
     * @return The extracted substring up to the first space character, or the entire string if no space is found.
     */
    private static String extractUntilSpace(String input) {
        int spaceIndex = input.indexOf(' ');
        return spaceIndex == -1 ? input : input.substring(0, spaceIndex);
    }
}
