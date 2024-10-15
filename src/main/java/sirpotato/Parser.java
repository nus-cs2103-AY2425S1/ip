package sirpotato;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Contains methods to parse user input and validate user input
 */
class Parser {

    /**
     * Validates the date that user inputs
     * If not in dd-MM-yyyy format, will throw an exception
     *
     * @param date the date(in string format) that the user types in
     */
    public static void validateDateFormat(String date) throws DukeException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            LocalDate.parse(date, dateFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Mate, the date must be in the format dd-MM-yyyy.");
        }
    }

    /**
     * Validates user input
     *
     * @param userInput The user's input to the chatbot
     */
    public static void checkForErrors(String userInput) throws DukeException {
        String[] sectionedString = userInput.trim().split("\\s+");

        switch (sectionedString[0]) {
        case "todo":
            checkForCommand(userInput, "todo", 5, "Mate, you have got to give us a description of the task");
            break;
        case "deadline":
            checkForCommand(userInput, "deadline", 9, "Mate, you need to give me a task description and deadline date");
            validateDeadlineFormat(userInput);
            break;
        case "event":
            checkForCommand(userInput, "event", 6,
                    "Mate, an event should have the description, the start, and the end.");
            validateEventFormat(userInput);
            break;
        case "delete":
            checkForCommand(userInput, "delete", 7,
                    "You need to say which item to delete");
            validateTaskNumber(sectionedString[1], "delete");
            break;
        case "sort":
            checkForCommand(userInput, "sort", 5,
                    "Mate, please specify your category: either description or deadline");
            validateSortCategory(sectionedString[1]);
            break;
        case "mark":
        case "unmark":
            checkForCommand(userInput, sectionedString[0], sectionedString[0].length(),
                    "Mate, please specify your task to mark/unmark");
            validateTaskNumber(sectionedString[1], sectionedString[0]);
            break;
        case "find":
            checkForCommand(userInput, "find", 5,
                    "Mate, please specify a keyword/string to search for");
            break;
        default:
            if (!userInput.trim().equals("bye") && !userInput.trim().equals("list")) {
                throw new DukeException("I'm sorry, that is not a valid input");
            }
        }
    }

    private static void checkForCommand(String userInput, String command,
            int minLength, String errorMsg) throws DukeException {
        if (!userInput.startsWith(command + " ")) {
            throw new DukeException("Mate, you may have misspelt or forgotten to type the rest of your command");
        }
    }

    private static void validateTaskNumber(String task, String command) throws DukeException {
        if (!task.matches("\\d+")) {
            throw new DukeException("Mate, please give a task number (not text) to " + command);
        }
    }

    // Helper method to validate deadline format
    private static void validateDeadlineFormat(String userInput) throws DukeException {
        String[] sectionedString = userInput.split("/by ");
        if (sectionedString.length < 2 || sectionedString[0].substring(9).isEmpty() || sectionedString[1].isEmpty()) {
            throw new DukeException("Mate, you need to give me a task description and deadline date");
        }
        validateDateFormat(sectionedString[1].trim());
    }

    // Helper method to validate event format
    private static void validateEventFormat(String userInput) throws DukeException {
        // Updated split to handle extra spaces around /from and /to
        String[] sectionedString = userInput.split("/from\\s+|\\s+/to\\s+");

        // Check if description is empty
        if (sectionedString[0].substring(6).trim().isEmpty()) {
            throw new DukeException("Mate, the event description cannot be empty.");
        }

        // Ensure both dates are provided and are valid
        if (sectionedString.length < 3 || sectionedString[1].trim().isEmpty() || sectionedString[2].trim().isEmpty()) {
            throw new DukeException("Mate, an event should have a start and an end date.");
        }

        // Validate the start and end dates
        validateDateFormat(sectionedString[1].trim());
        validateDateFormat(sectionedString[2].trim());
    }

    // Helper method to validate sort category
    private static void validateSortCategory(String category) throws DukeException {
        if (!(category.equals("description") || category.equals("deadline"))) {
            throw new DukeException("You have to sort by description or deadline");
        }
    }


    /**
     * Parses the date given into dd-MM-yyyy format
     *
     * @param dateToParse The string date that we wish to parse into dd-MM-yyyy format
     * @return the LocalDate object representing the dd-MM-yyyy date
     */
    public static LocalDate parseDate(String dateToParse) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(dateToParse, formatter);
    }

    /**
     * Parses the user input and returns the command to be executed
     *
     * @param userInput User's input to the chatbot
     * @return Command object to be executed i.e AddCommand or MarkCommand
     * @throws DukeException if the input is not valid
     */
    public static Command parseCommand(String userInput) throws DukeException {
        userInput = userInput.trim();
        checkForErrors(userInput);

        if (userInput.trim().equals("bye")) {
            return new ExitCommand();
        } else if (userInput.trim().equals("list")) {
            return new ListCommand();
        } else if (userInput.startsWith("mark")) {
            int itemNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
            return new MarkCommand(itemNumber);
        } else if (userInput.startsWith("unmark")) {
            int itemNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
            return new UnmarkCommand(itemNumber);
        } else if (userInput.startsWith("todo")) {
            String description = userInput.substring(5);
            return new AddCommand(new Todo(description));
        } else if (userInput.startsWith("find")) {
            String searchString = userInput.substring(5);
            return new FindCommand(searchString);
        } else if (userInput.startsWith("deadline")) {
            String[] sectionedString = userInput.split("/by ");
            String description = sectionedString[0].substring(9);
            LocalDate by = parseDate(sectionedString[1].trim());
            return new AddCommand(new Deadline(description, by));
        } else if (userInput.startsWith("event")) {
            String[] sectionedString = userInput.split("\\s+/from\\s+|\\s+/to\\s+");
            String description = sectionedString[0].substring(6);
            LocalDate from = parseDate(sectionedString[1]);
            LocalDate to = parseDate(sectionedString[2]);
            return new AddCommand(new Event(description, from, to));
        } else if (userInput.startsWith("delete")) {
            String[] sectionedString = userInput.split(" ");
            int itemToDelete = Integer.parseInt(sectionedString[1]) - 1;
            return new DeleteCommand(itemToDelete);
        } else if (userInput.startsWith("sort")) {
            String[] sectionedString = userInput.split(" ");
            String categoryToSortBy = sectionedString[1];
            return new SortCommand(categoryToSortBy);
        } else {
            throw new DukeException("That is not valid input mate. Please have another go.");
        }
    }
}
