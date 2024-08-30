package sirpotato;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

class Parser {

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
     * @param userInput The user's input to the chatbot
     */
    public static void checkForErrors(String userInput) throws DukeException {
        if (userInput.startsWith("todo")) {
            if (userInput.length() <= 5 || userInput.substring(5).isEmpty()) {
                throw new DukeException("Mate, you have got to give us a description of the task");
            }
        } else if (userInput.startsWith("deadline")) {
            String[] sectionedString = userInput.split("/by ");
            if (sectionedString.length < 2 || 
                sectionedString[0].substring(9).isEmpty() || 
                sectionedString[1].isEmpty()) {
                throw new DukeException("Mate, a deadline must include a task, and the deadline");
            }
            validateDateFormat(sectionedString[1].trim());
        } else if (userInput.startsWith("event")) {
            String[] sectionedString = userInput.split(" /from | /to ");
            if (sectionedString.length < 3 || sectionedString[0].substring(6).isEmpty() ||
                sectionedString[1].isEmpty() || sectionedString[2].isEmpty()) {
                throw new DukeException("Mate, an event should have the description, the start, and the end.");
            }
            validateDateFormat(sectionedString[1].trim());
            validateDateFormat(sectionedString[2].trim());
        } else if (userInput.startsWith("delete")) {
            if (userInput.length() <= 7) {
                throw new DukeException("You need to say which item to delete");
            }
        } else if (!userInput.equals("bye") && !userInput.equals("list") && !userInput.startsWith("mark") && !userInput.startsWith("unmark")) {
            throw new DukeException("I'm sorry, that is not a valid input");
        }
    }

    public static LocalDate parseData(String dateToParse) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(dateToParse, formatter);
    }

    public static Command parseCommand(String userInput) throws DukeException {
        checkForErrors(userInput);

        if (userInput.equals("bye")) {
            return new ExitCommand();
        } else if (userInput.equals("list")) {
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
        } else if (userInput.startsWith("deadline")) {
            String[] sectionedString = userInput.split("/by ");
            String description = sectionedString[0].substring(9);
            LocalDate by = parseData(sectionedString[1].trim());
            return new AddCommand(new Deadline(description, by));
        } else if (userInput.startsWith("event")) {
            String[] sectionedString = userInput.split(" /from | /to ");
            String description = sectionedString[0].substring(6);
            LocalDate from = parseData(sectionedString[1]);
            LocalDate to = parseData(sectionedString[2]);
            return new AddCommand(new Event(description, from, to));
        } else if (userInput.startsWith("delete")) {
            String[] sectionedString = userInput.split(" ");
            int itemToDelete = Integer.parseInt(sectionedString[1]) - 1;
            return new DeleteCommand(itemToDelete);
        } else {
            throw new DukeException("That is not valid input mate. Please have another go.");
        }
    }
}