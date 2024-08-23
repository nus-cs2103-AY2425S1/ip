package commands;

import exceptions.ArgumentNotFoundException;
import exceptions.BottyException;
import exceptions.EmptyCommandException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParsedInput {
    private final Map<String, String> arguments;
    private final String command;

    private ParsedInput(String command, Map<String, String> arguments) {
        this.command = command;
        this.arguments = arguments;
    }
    public String getCommand() {
        return command;
    }
    public String getArgument(String flag) throws ArgumentNotFoundException {
        if (!arguments.containsKey(flag)) {
            throw new ArgumentNotFoundException(flag);
        }
        return arguments.get(flag);
    }
    public static ParsedInput parse(String input) throws BottyException {
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
    private static boolean isFlag(String word) {
        return word.matches("/(.*)");
    }
    private static String getFlag(String word) {
        return word.replaceAll("/(.*)", "$1");
    }
}
