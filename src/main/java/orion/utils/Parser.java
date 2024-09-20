package orion.utils;

import orion.commands.AddDeadlineCommand;
import orion.commands.AddEventCommand;
import orion.commands.AddTodoCommand;
import orion.commands.Command;
import orion.commands.DeleteTaskCommand;
import orion.commands.ExitCommand;
import orion.commands.FindCommand;
import orion.commands.HelpCommand;
import orion.commands.MarkTaskCommand;
import orion.commands.PrintTasksCommand;
import orion.commands.UnmarkTaskCommand;
import orion.exceptions.OrionException;
import orion.exceptions.OrionInputException;

/**
 * The {@code Parser} class is responsible for parsing user input into executable commands.
 * It processes input strings and returns the appropriate {@link Command} object.
 */
public class Parser {

    /**
     * Constructs a new {@code Parser} object.
     */
    public Parser() {

    }

    /**
     * Removes the first word from a given string.
     *
     * @param str the string from which the first word is to be removed
     * @return the string with the first word removed
     */
    public static String removeFirstWordFromString(String str) {
        return str.split(" ", 2)[1];
    }

    /**
     * Parses the user input and returns the appropriate {@link Command} object.
     *
     * @param input the user input string to parse
     * @return the corresponding {@link Command} object
     * @throws OrionException if the input is invalid or cannot be parsed into a valid command
     */
    public static Command parse(String input) throws OrionException {
        assert input != null : "input must not be null";
        String[] inputArray = input.split(" ");
        String command = inputArray[0].toLowerCase();
        String[] parsedInputArray = input.split("/");

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "help":
            return new HelpCommand();
        case "list":
            return new PrintTasksCommand();
        case "mark":
            return new MarkTaskCommand(inputArray);
        case "unmark":
            return new UnmarkTaskCommand(inputArray);
        case "todo":
            return new AddTodoCommand(inputArray);
        case "deadline":
            return new AddDeadlineCommand(parsedInputArray);
        case "event":
            return new AddEventCommand(parsedInputArray);
        case "delete":
            return new DeleteTaskCommand(inputArray);
        case "find":
            return new FindCommand(inputArray);
        default:
            throw new OrionInputException("Please provide a supported command!");
        }
    }
}
