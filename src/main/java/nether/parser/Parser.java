package nether.parser;

import nether.NetherException;
import nether.command.AddCommand;
import nether.command.Command;
import nether.command.DeleteCommand;
import nether.command.ExitCommand;
import nether.command.FindCommand;
import nether.command.ListCommand;
import nether.command.MarkDoneCommand;
import nether.command.MarkNotDoneCommand;
import nether.task.DeadlineTask;
import nether.task.EventTask;
import nether.task.TodoTask;

/**
 * Handles the parsing of user input into commands and arguments that the program can understand.
 * The Parser class provides methods to interpret different types of tasks and extract relevant details.
 */

public class Parser {
    /**
     * Parses the user input to identify the {@code Command} and extracts details relevant to the {@code Command}.
     *
     * @param userInput The full input string provided by the user (without trailing or leading whitespaces).
     * @return An array of strings containing the parts of the user input necessary to create tasks.
     * @throws NetherException If the command is not recognized or the input format is incorrect.
     */
    public Command parse(String userInput) throws NetherException {
        String[] processedInput;
        String commandWord = extractCommandWord(userInput);

        switch (commandWord) {
        case "list":
            return new ListCommand();
        case "todo":
            processedInput = extractInputDetails(userInput, "todo");
            return new AddCommand(new TodoTask(processedInput[0]));
        case "deadline":
            processedInput = extractInputDetails(userInput, "deadline");
            return new AddCommand(new DeadlineTask(processedInput[0], processedInput[1]));
        case "event":
            processedInput = extractInputDetails(userInput, "event");
            return new AddCommand(new EventTask(processedInput[0], processedInput[1], processedInput[2]));
        case "mark":
            return new MarkDoneCommand(extractTaskNumber(userInput));
        case "unmark":
            return new MarkNotDoneCommand(extractTaskNumber(userInput));
        case "delete":
            return new DeleteCommand(extractTaskNumber(userInput));
        case "find":
            processedInput = extractInputDetails(userInput, "find");
            return new FindCommand(processedInput[0]);
        case "bye":
            return new ExitCommand();
        default:
            throw new NetherException("the command: '" + userInput + "' is not in our database.");
        }

    }

    /**
     * Extracts the {@code Command} from the user's input string. The command is assumed to be the
     * first word of the input.
     *
     * @param userInput The full input string provided by the user.
     * @return The command in lowercase (e.g., "todo", "deadline", or "event").
     */
    public String extractCommandWord(String userInput) {
        return userInput.split(" ", 2)[0].toLowerCase();
    }

    /**
     * Processes the user input into parts, making it easier to instantiate the respective {@code Task} objects.
     * Splits the input based on the command and uses regex to identify {@code Task} details.
     *
     * @param userInput The full input string provided by the user (without leading or trailing whitespaces).
     * @param taskType The type of task ("todo", "deadline", or "event").
     * @return A string array containing the task details to be instantiated by Nether class.
     * @throws NetherException If the input does not follow the expected format or required details are missing.
     */

    public String[] extractInputDetails(String userInput, String taskType) throws NetherException {
        String[] preprocessArray; // stores the full input without the command word
        String[] resultArray; // stores the split input formatted accordingly to the task type

        switch (taskType) {
        case "todo":
            return handleTodoDetails(userInput);
        case "deadline":
            return handleDeadlineDetails(userInput);
        case "event":
            return handleEventDetails(userInput);
        case "find":
            return handleFindDetails(userInput);
        default:
            throw new NetherException("the command: '" + userInput + "' is not in our database");
        }
    }

    /**
     * Parses the user input for "todo" commands.
     *
     * @param userInput The full input string provided by the user.
     * @return An array containing the details of the todo task.
     * @throws NetherException If the todo task's description is empty.
     */
    private static String[] handleTodoDetails(String userInput) {
        String[] todoDetails = userInput.split("(?i)todo ", 2);
        if (todoDetails.length < 2 || todoDetails[1].trim().isEmpty()) {
            throw new NetherException("the description of a todo cannot be empty.");
        }
        return new String[]{todoDetails[1]};
    }

    /**
     * Parses the user input for "deadline" commands.
     *
     * @param userInput The full input string provided by the user.
     * @return An array containing the description and due date of the deadline task.
     * @throws NetherException If either the description or the due date is empty.
     */
    private static String[] handleDeadlineDetails(String userInput) {
        String[] deadlineDetails = userInput.split("(?i)deadline ", 2);
        if (deadlineDetails.length < 2 || deadlineDetails[1].trim().isEmpty()) {
            throw new NetherException("the description of a deadline cannot be empty.");
        }
        String[] deadlineParts = deadlineDetails[1].split("/by ", 2);
        if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
            throw new NetherException("the description or date/time of a deadline cannot be empty.");
        }
        return new String[] {deadlineParts[0], deadlineParts[1]};
    }

    /**
     * Parses the user input for "event" commands.
     *
     * @param userInput The full input string provided by the user.
     * @return An array containing the description, start time, and end time of event.
     * @throws NetherException If the description, start time, or end time is empty.
     */
    private static String[] handleEventDetails(String userInput) {
        String[] eventDetails = userInput.split("(?i)event ", 2);
        if (eventDetails.length < 2 || eventDetails[1].trim().isEmpty()) {
            throw new NetherException("the description of an event cannot be empty.");
        }
        String[] eventParts = eventDetails[1].split("/from |/to ", 3);
        if (eventParts.length < 3 || eventParts[0].trim().isEmpty() || eventParts[1].trim().isEmpty()
                || eventParts[2].trim().isEmpty()) {
            throw new NetherException(
                    "the description, start time, or end time of an event cannot be empty.");
        }
        return new String[]{eventParts[0], eventParts[1], eventParts[2]};
    }

    /**
     * Parses the user input for "find" commands.
     *
     * @param userInput The full input string provided by the user.
     * @return An array containing the search keyword
     * @throws NetherException If the keyword for searching is empty.
     */
    private static String[] handleFindDetails(String userInput) {
        String[] findDetails = userInput.split("(?i)find", 2);
        if (findDetails.length < 2 || findDetails[1].trim().isEmpty()) {
            throw new NetherException("please enter a keyword for me to search.");
        }
        return new String[]{findDetails[1].trim()};
    }

    /**
     * Returns the index/number of the {@code Task} stated in the user input.
     * Useful for commands like {@code mark} or {@code unmark}.
     *
     * @param userInput The input string provided by the user.
     * @return The task number (index + 1) to be marked/unmarked if successfully parsed; -1 otherwise.
     */

    public int extractTaskNumber(String userInput) {
        try {
            String[] parts = userInput.split(" ");
            return Integer.parseInt(parts[1]);
        } catch (Exception e) {
            return -1;
        }
    }
}
