package botty.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import botty.exceptions.ArgumentNotFoundException;
import botty.exceptions.EmptyCommandException;

/**
 * Holds the data involved in a user input
 */
public class ParsedInput {
    // The arguments of the input
    private final Map<String, String> arguments;
    // The command being called
    private final String command;

    /**
     * Default constructor for {@code ParsedInput}.
     * @param command the command.
     * @param arguments the arguments.
     */
    private ParsedInput(String command, Map<String, String> arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    /**
     * Returns the command.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Returns the argument based on the given flag.
     * @param flag the flag of the argument.
     * @return the argument associated with the given flag.
     * @throws ArgumentNotFoundException when there is no argument with the given flag.
     */
    public String getArgument(String flag) throws ArgumentNotFoundException {
        if (!arguments.containsKey(flag)) {
            throw new ArgumentNotFoundException(flag);
        }
        return arguments.get(flag);
    }

    /**
     * Returns the argument based on the given flag or null if not found
     * @param flag the flag of the argument.
     * @return the argument associated with the given flag or null if not found
     */
    public String getArgumentOrNull(String flag) {
        if (!arguments.containsKey(flag)) {
            return null;
        }
        return arguments.get(flag);
    }

    /**
     * Parses the given input string to return a {@code ParsedInput} with the appropriate command and arguments.
     * @param input the string input.
     * @return a {@code ParsedInput} with the given command and arguments.
     * @throws EmptyCommandException if the command is empty.
     */
    public static ParsedInput parse(String input) throws EmptyCommandException {
        input = input.trim();

        if (input.isEmpty()) {
            throw new EmptyCommandException();
        }

        String command = extractCommand(input);
        String arguments = extractArguments(input);

        Map<String, String> argumentMap = parseArguments(arguments);

        return new ParsedInput(command, argumentMap);
    }

    /**
     * Extracts the command from the input string.
     * @param input the full input string.
     * @return the command.
     */
    private static String extractCommand(String input) {
        return input.split("\\s+", 2)[0];
    }

    /**
     * Extracts the arguments from the input string.
     * @param input the full input string.
     * @return the arguments part of the input.
     */
    private static String extractArguments(String input) {
        String[] splitInput = input.split("\\s+", 2);
        return splitInput.length > 1 ? splitInput[1] : "";
    }

    /**
     * Parses the arguments part of the input string into a map of flags and their corresponding values.
     * @param arguments the arguments part of the input.
     * @return a map of flags to argument values.
     */
    private static Map<String, String> parseArguments(String arguments) {
        Map<String, String> argumentMap = new HashMap<>();

        List<String> currentArgumentValue = new ArrayList<>();
        String currentFlag = "main";

        String[] words = arguments.split("\\s+");
        for (String word : words) {
            if (isFlag(word)) {
                storeArgument(argumentMap, currentFlag, currentArgumentValue);
                currentFlag = extractFlag(word);
                currentArgumentValue = new ArrayList<>();
            } else {
                currentArgumentValue.add(word);
            }
        }
        storeArgument(argumentMap, currentFlag, currentArgumentValue);

        return argumentMap;
    }

    /**
     * Stores the collected words as an argument in the argument map.
     * @param argumentMap the map to store arguments.
     * @param flag the flag under which to store the argument.
     * @param words the list of words constituting the argument value.
     */
    private static void storeArgument(Map<String, String> argumentMap, String flag, List<String> words) {
        String argument = String.join(" ", words).trim();
        if (!argument.isEmpty()) {
            argumentMap.put(flag, argument);
        }
    }

    /**
     * Returns if the given word is in the format of a flag.
     * @param word the word being tested.
     * @return if the word is a flag.
     */
    private static boolean isFlag(String word) {
        return word.matches("/(.*)");
    }

    /**
     * Returns the flag given a word in flag format.
     * @param word the word in flag format.
     * @return the flag from the word.
     */
    private static String extractFlag(String word) {
        return word.replaceAll("/(.*)", "$1");
    }

    /**
     * Renames the main flag to the given flag name
     * @param newFlag the name to rename the main flag to
     */
    public void changeMainFlag(String newFlag) {
        arguments.put(newFlag, arguments.get("main"));
    }
}
