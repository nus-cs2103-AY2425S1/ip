package maga.middleware;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import maga.commands.Command;
import maga.exceptions.InvalidCommandException;

/**
 * The {@code Parser} class is responsible for interpreting user input and converting it
 * into commands that the {@code TaskManager} can execute. It handles commands for tasks such as
 * listing, marking, unmarking, deleting, finding, and adding tasks like todo, event, and deadline tasks.
 */
public class Parser {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructs a new {@code Parser} instance.
     * This constructor initializes the parser with a default date formatter.
     */
    public Parser() {
    }

    private Command<Integer> listTasks() {
        return new Command<>("list", null);
    }

    private Command<Integer> markTask(String input) {
        char[] charArray = input.toCharArray();
        int taskNumber = Character.getNumericValue(charArray[charArray.length - 1]) - 1;
        return new Command<>("mark", taskNumber);
    }

    private Command<Integer> unmarkTask(String input) {
        char[] charArray = input.toCharArray();
        int taskNumber = Character.getNumericValue(charArray[charArray.length - 1]) - 1;
        return new Command<>("unmark", taskNumber);
    }

    private Command<Integer> deleteTask(String input) {
        String description = input.substring(7).trim();
        int taskNumber = Integer.parseInt(description) - 1;
        return new Command<>("delete", taskNumber);
    }

    private Command<Integer> exitBot(String input) {
        return new Command<>("bye", null);
    }

    private Command<String> findTask(String input) {
        String description = input.substring(5).trim();
        return new Command<>("find", description);
    }

    private Command<LocalDate[]> addTodoTask(String input) {
        String description = input.substring(5).trim();
        return new Command<>("todo", description, null);
    }

    private Command<LocalDate[]> addEventTask(String input) throws DateTimeParseException {
        String description = input.substring(6).trim();
        String[] descriptionArray = description.split("/");
        LocalDate[] date = new LocalDate[1];
        try {
            date[0] = LocalDate.parse(descriptionArray[1], FORMATTER);
        } catch (DateTimeParseException e) {
            throw e;
        }
        return new Command<>("event", descriptionArray[0], date);
    }

    private Command<LocalDate[]> addDeadlineTask(String input) throws DateTimeParseException {
        String description = input.substring(9).trim();
        String[] descriptionArray = description.split("/");
        LocalDate fromDate;
        LocalDate toDate;
        try {
            fromDate = LocalDate.parse(descriptionArray[1], FORMATTER);
            toDate = LocalDate.parse(descriptionArray[2], FORMATTER);
        } catch (DateTimeParseException e) {
            throw e;
        }

        LocalDate[] dateArray = {fromDate, toDate};
        return new Command<>("deadline", descriptionArray[0], dateArray);
    }


    /**
     * Parses and processes user input to determine which command to execute.
     * This method supports commands such as 'list', 'mark', 'unmark', 'delete', 'find',
     * 'todo', 'event', and 'deadline'.
     *
     * @param input The user's input as a string.
     * @return A {@code Command} object representing the action to be performed.
     * @throws InvalidCommandException If the input does not match any known command.
     * @throws DateTimeParseException If the input contains an invalid date format.
     */
    public Command<?> handleInput(String input) throws InvalidCommandException, DateTimeParseException {
        input = input.toLowerCase();
        String command = input.split(" ")[0];

        return switch (command) {
        case "list" -> listTasks();
        case "mark" -> markTask(input);
        case "unmark" -> unmarkTask(input);
        case "delete" -> deleteTask(input);
        case "find" -> findTask(input);
        case "todo" -> addTodoTask(input);
        case "event" -> addEventTask(input);
        case "deadline" -> addDeadlineTask(input);
        case "bye" -> exitBot(input);
        default -> throw new InvalidCommandException();
        };
    }
}
