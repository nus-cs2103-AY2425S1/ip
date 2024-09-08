package botty.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import botty.exceptions.ArgumentNotFoundException;
import botty.exceptions.BottyException;
import botty.exceptions.EmptyCommandException;

/**
 * Defines the behaviour of a ParsedInput
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
     * Parses the given input string to return a {@code ParsedInput} with the appropriate command and arguments.
     * @param input the string input.
     * @return a {@code ParsedInput} with the given command and arguments.
     * @throws BottyException if the command or arguments are invalid.
     */
    public static ParsedInput parse(String input) throws EmptyCommandException {
        // take in user input and return a command input
        // that splits all the argument

        // ex. deadline finish homework /by tomorrow

        // first, process the input
        input = input.trim();

        // ensure that the command is not empty
        if (input.isEmpty()) {
            throw new EmptyCommandException();
        }

        String[] splitInput = input.split("\\s+");

        String command = splitInput[0];

        Map<String, String> argumentMap = new HashMap<>();


        ArrayList<String> currentArgumentWordList = new ArrayList<>();
        String currentArgumentFlag = "main";

        for (int i = 1; i < splitInput.length; i++) {
            if (isFlag(splitInput[i])) {
                String argument = String.join(" ", currentArgumentWordList);
                argumentMap.put(currentArgumentFlag, argument);
                currentArgumentFlag = getFlag(splitInput[i]);

                currentArgumentWordList = new ArrayList<>();
            } else {
                currentArgumentWordList.add(splitInput[i]);
            }
        }
        String argument = String.join(" ", currentArgumentWordList);
        argumentMap.put(currentArgumentFlag, argument);

        return new ParsedInput(command, argumentMap);
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
    private static String getFlag(String word) {
        return word.replaceAll("/(.*)", "$1");
    }
}
