package cook;

import java.util.HashMap;

import commands.ByeCommand;
import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.ToDoCommand;
import commands.UnmarkCommand;
import exceptions.InvalidCommandException;
import exceptions.InvalidInputException;

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

    private Command createCommand(HashMap<String, String> argumentsHashMap) throws InvalidInputException,
            InvalidCommandException {
        String command = argumentsHashMap.get("command");
        switch (command) {
        case "bye" -> {
            return new ByeCommand();
        }
        case "list" -> {
            return new ListCommand();
        }
        case "mark" -> {
            return new MarkCommand(argumentsHashMap.get("mark"));
        }
        case "unmark" -> {
            return new UnmarkCommand(argumentsHashMap.get("unmark"));
        }
        case "delete" -> {
            return new DeleteCommand(argumentsHashMap.get("delete"));
        }
        case "find" -> {
            return new FindCommand(argumentsHashMap.get("find"));
        }
        case "todo" -> {
            return new ToDoCommand(argumentsHashMap.get("todo"));
        }
        case "deadline" -> {
            return new DeadlineCommand(argumentsHashMap.get("deadline"), argumentsHashMap.get("/by"));
        }
        case "event" -> {
            return new EventCommand(argumentsHashMap.get("event"), argumentsHashMap.get("from"),
                    argumentsHashMap.get("to"));
        }
        default -> {
            throw new InvalidCommandException("The command does not match any that I know of.");
        }
        }
    }

    /**
     * Reads input and returns in an understandable format
     *
     * @param input Input to format.
     * @return HashMap of commands and their corresponding inputs.
     * @throws InvalidCommandException If command is not recognized.
     */
    public Command readInput(String input) throws InvalidInputException, InvalidCommandException {
        String[] arguments = input.split(" ");
        String command = arguments[0].toLowerCase();
        HashMap<String, String> argumentsHashMap = new HashMap<>();

        if (!this.commandArgs.containsKey(command)) {
            throw new InvalidCommandException("The command does not match any that I know of.");
        }

        int argsExpected = this.commandArgs.get(command);
        argumentsHashMap.put("command", command);

        if (arguments.length < argsExpected) {
            throw new InvalidCommandException("The command should have at least " + argsExpected + " arguments.");
        } else if (argsExpected == 2) {
            argumentsHashMap.put(command, input.replace(command, "").strip());
            return createCommand(argumentsHashMap);
        }
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
        return createCommand(argumentsHashMap);
    }
}
