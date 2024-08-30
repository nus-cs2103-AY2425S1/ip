package duke.parser;

import java.time.LocalDateTime;

import duke.commands.AddTaskCommand;
import duke.commands.Command;
import duke.commands.DeleteTaskCommand;
import duke.commands.FilterTaskCommand;
import duke.commands.FindTaskCommand;
import duke.commands.ListTaskCommand;
import duke.commands.MarkTaskCommand;
import duke.exceptions.InvalidInputException;

/**
 * The {@code InputParser} class is responsible for parsing user input and
 * converting it into the corresponding command objects.
 * <p>
 * This class interprets commands for listing, marking, unmarking, deleting,
 * filtering tasks, and adding new tasks based on the user's input.
 * </p>
 */
public class InputParser {

    /**
     * Parses the user's input string and returns the corresponding {@link Command}.
     * <p>
     * The parser determines the type of command (e.g., list, mark, unmark, delete, filter, or add task)
     * based on the input string provided by the user. If the input does not match any recognized
     * command, it assumes the input is for adding a new task.
     * </p>
     *
     * @param userInput The raw input string from the user.
     * @return The {@link Command} object representing the action to be taken based on the user input.
     * @throws InvalidInputException if the input format is invalid or cannot be parsed correctly.
     */
    public static Command parseUserInput(String userInput) throws InvalidInputException {
        if (userInput.equals("list")) {
            return new ListTaskCommand();
        } else if (userInput.startsWith("unmark")) {
            return new MarkTaskCommand(false, InputParser.parseTaskIndex(userInput));
        } else if (userInput.startsWith("mark")) {
            return new MarkTaskCommand(true, InputParser.parseTaskIndex(userInput));
        } else if (userInput.startsWith("delete")) {
            return new DeleteTaskCommand(InputParser.parseTaskIndex(userInput));
        } else if (userInput.startsWith("filter")) {
            String dateString = InputParser.parseCommandArgument(userInput, "Invalid date format.");
            LocalDateTime dateTime = duke.parser.DateTimeFormatEnum.parse(dateString)
                    .orElseThrow(() -> new InvalidInputException("Invalid date format."));
            return new FilterTaskCommand(dateTime);
        } else if (userInput.startsWith("find")) {
            String keyword = InputParser.parseCommandArgument(userInput, "Invalid find command format.");
            return new FindTaskCommand(keyword);
        } else { // we try to add a task (todos/deadline/event)
            return new AddTaskCommand(userInput);
        }
    }

    /**
     * Extracts the task index from the user's input string.
     * <p>
     * This method assumes that the user input contains a valid command followed by
     * an integer representing the task index (e.g., "delete 2"). The method returns
     * the parsed index (0-based) by subtracting 1 from the input number.
     * </p>
     *
     * @param userInput The raw input string from the user, typically containing the command and a task index.
     * @return The task index as a 0-based integer.
     * @throws InvalidInputException if the input does not contain a valid integer index or is improperly formatted.
     */
    public static int parseTaskIndex(String userInput) throws InvalidInputException {
        try {
            return Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidInputException("Your input is invalid.\n" + e.getMessage());
        }
    }

    /**
     * Extracts the argument from the user's input string.
     * <p>
     * This method is used to extract arguments from commands such as "filter" or "find".
     * It returns the part of the string following the command keyword.
     * </p>
     *
     * @param userInput The raw input string from the user.
     * @param errorMessage The error message to display if the argument cannot be extracted.
     * @return The extracted argument as a string.
     * @throws InvalidInputException if the input does not contain a valid argument or is improperly formatted.
     */
    private static String parseCommandArgument(String userInput, String errorMessage) throws InvalidInputException {
        try {
            return userInput.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidInputException(errorMessage + "\n" + e.getMessage());
        }
    }
}
