package cook;

import java.util.HashMap;

import exceptions.InvalidCommandException;

/**
 * Parser class to format inputs from the user.
 */
public class Parser {
    private HashMap<String, Integer> commandArgs;

    /**
     * Constructor for Parser class.
     *
     * @param commandArgs Stores commands and their expected number of arguments.
     */
    public Parser(HashMap<String, Integer> commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Constructor for Parser class.
     *
     * @param input Input to format.
     * @return HashMap of commands and their corresponding inputs.
     * @throws InvalidCommandException If command is not recognized.
     */
    public HashMap<String, String> readInput(String input) throws InvalidCommandException {
        String[] arguments = input.split(" ");
        String command = arguments[0].toLowerCase();
        HashMap<String, String> argumentsHashMap = new HashMap<>();

        if (this.commandArgs.containsKey(command)) {
            int argsExpected = this.commandArgs.get(command);
            argumentsHashMap.put("command", command);

            if (arguments.length < argsExpected) {
                throw new InvalidCommandException("The command should have at least "
                        + argsExpected + " arguments.");
            } else if (argsExpected == 2) {
                argumentsHashMap.put(command, input.replace(command, "").strip());
            } else if (argsExpected > 3) {
                String commandKey = command;
                StringBuilder valueSB = new StringBuilder();

                for (int i = 1; i < arguments.length; i++) {
                    String currentArgument = arguments[i];

                    if (!currentArgument.startsWith("/")) {
                        valueSB.append(currentArgument).append(" ");
                    }

                    if (currentArgument.startsWith("/") || i + 1 == arguments.length) {
                        argumentsHashMap.put(commandKey.toLowerCase(), valueSB.toString().strip());
                        commandKey = currentArgument;
                        valueSB.setLength(0);
                    }
                }
            }
            return argumentsHashMap;
        }
        throw new InvalidCommandException("The command does not match any that I know of.");
    }
}
