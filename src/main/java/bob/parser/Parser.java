package bob.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import bob.command.ByeCommand;
import bob.command.Command;
import bob.command.DeadlineCommand;
import bob.command.DeleteCommand;
import bob.command.EventCommand;
import bob.command.FindCommand;
import bob.command.ListCommand;
import bob.command.MarkCommand;
import bob.command.TagCommand;
import bob.command.TodoCommand;
import bob.command.UntagCommand;
import bob.exception.InvalidTaskException;
import bob.ui.Ui;

/**
 * Handles user commands.
 */
public class Parser {

    /**
     * Parses string format of date and returns a LocalDate object.
     *
     * @param date String format of date.
     * @return LocalDate object.
     */
    @SuppressWarnings("checkstyle:EmptyCatchBlock")
    public static LocalDate parseDate(String date) {
        List<DateTimeFormatter> inputFormatters = List.of(
                DateTimeFormatter.ofPattern("yyyy-MM-dd"), //type 1 date format
                DateTimeFormatter.ofPattern("dd-MM-yyyy"), //type 2 date format
                DateTimeFormatter.ofPattern("dd/MM/yyyy"), //type 3 date format
                DateTimeFormatter.ofPattern("dd-MMM-yyyy") //type 4 date format
        );
        DateTimeParseException dateTimeParseException = null;
        for (DateTimeFormatter formatter : inputFormatters) {
            try {
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                dateTimeParseException = e;
            }
        }
        return null;
    }


    /**
     * Parses the input given by user.
     * @param input Input by user.
     * @return Command to be executed.
     */
    public static Command parseCommand(String input) {
        String[] inputWordsList = Parser.parseInputIntoStringArray(input);
        String keyword = inputWordsList[0];

        switch (keyword) {
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(input, true);
        case "unmark":
            return new MarkCommand(input, false);
        case "delete":
            return new DeleteCommand(input);
        case "event":
            return new EventCommand(input);
        case "deadline":
            return new DeadlineCommand(input);
        case "todo":
            return new TodoCommand(input);
        case "find":
            return new FindCommand(input);
        case "tag":
            return new TagCommand(input);
        case "untag":
            return new UntagCommand(input);
        case "bye":
            return new ByeCommand(input);
        default:
            Ui.requestValidCommand();
            return new Command(input);
        }
    }

    /**
     * Parses Description from Input
     *
     * @param input Input command given by user.
     * @return Task description only.
     * @throws InvalidTaskException
     */
    public static String parseDescriptionFromInput(String input) throws InvalidTaskException {
        String[] arrayOfCommandAndRemainingInput = input.split(" ", 2); //extract the keyword
        String command = arrayOfCommandAndRemainingInput[0];
        switch (command) {
        case "todo":
            if (arrayOfCommandAndRemainingInput.length == 1) {
                throw new InvalidTaskException("OOPS!!! The description of todo cannot be empty.");
            }
            String todoDescription = arrayOfCommandAndRemainingInput[1];
            return todoDescription;
        case "deadline":
            if (arrayOfCommandAndRemainingInput.length == 1) {
                throw new InvalidTaskException("OOPS!!! The description of deadline cannot be empty.");
            }
            String[] deadlineSubString = arrayOfCommandAndRemainingInput[1].split("/by");
            if (deadlineSubString.length <= 1) {
                throw new InvalidTaskException("Invalid use of deadline. Should be '<event> /by <date>'.");
            }
            String deadlineDecription = deadlineSubString[0].trim();
            return deadlineDecription;
        case "event":
            if (arrayOfCommandAndRemainingInput.length == 1) {
                throw new InvalidTaskException("OOPS!!! The description of event cannot be empty.");
            }
            String[] eventSubString = arrayOfCommandAndRemainingInput[1].split("/from");
            if (eventSubString.length <= 1) {
                if (eventSubString.length == 0) {
                    throw new InvalidTaskException("OOPS!!! The event description cannot be empty.");
                }
                throw new InvalidTaskException("Invalid use of event format."
                        + "Should be '<description> /from <day> <start_time> /to <end_time>'");
            }
            String eventDescription = eventSubString[0].trim();
            return eventDescription;
        default:
            return input;
        }
    }

    /**
     * Parses Date from Input
     *
     * @param input Input command given by user.
     * @return Task date only.
     * @throws InvalidTaskException
     */
    public static LocalDate parseDateFromInput(String input) throws InvalidTaskException {
        String[] inputArray = Parser.parseInputIntoStringArray(input); //separate the keyword from the rest of string
        String command = inputArray[0];
        switch (command) {
        case "deadline":
            String dueDate = inputArray[Arrays.asList(inputArray).indexOf("/by") + 1];
            LocalDate deadline = Parser.parseDate(dueDate);
            if (deadline == null) {
                throw new InvalidTaskException("Invalid date format: "
                        + dueDate
                        + "\nSupported date formats are: \n"
                        + "yyyy-MM-dd, dd-MM-yyyy, dd/MM/yyyy, MMM dd yyyy");
            }
            return deadline;
        default:
            return LocalDate.now();
        }
    }

    public static String[] parseInputIntoStringArray(String input) {
        return input.split("\s+");
    }

    /**
     * Returns the task number based on the command given.
     * Currently only applicable to mark, unmark, delete command.
     *
     * @param input Command input by user.
     * @return
     */
    public static int parseTaskNumberFromInput(String input) {
        String[] separateKeywordFromTaskNumber = input.split(" ", 2);
        try {
            return Integer.parseInt(separateKeywordFromTaskNumber[1]);
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    /**
     * Returns the keyword specified in the input command.
     *
     * @param input
     * @return
     */
    public static String parseSearchKeywordFromInput(String input) {
        String[] separateKeywordFromTaskNumber = input.split(" ", 2);
        String searchKeyword = separateKeywordFromTaskNumber[1];
        return searchKeyword;
    }
}
