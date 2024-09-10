package bobby.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import bobby.command.Command;
import bobby.exceptions.BobbyException;
import bobby.exceptions.EmptyDeadlineException;
import bobby.exceptions.EmptyEventException;
import bobby.exceptions.EmptyTodoException;
import bobby.exceptions.InvalidDateException;
import bobby.exceptions.InvalidInputException;
import bobby.tasklist.TaskList;
import bobby.tasks.Deadline;
import bobby.tasks.Event;
import bobby.tasks.Task;
import bobby.tasks.Todo;


/**
 * The {@code Parser} class is responsible for interpreting and converting
 * user input into actionable commands and tasks. It contains methods to parse
 * user input strings to determine the appropriate {@code Command} or to create
 * specific types of {@code Task} objects, such as {@code Todo}, {@code Deadline},
 * and {@code Event}.
 * <p>
 * The {@code Parser} class also handles input validation and throws specific exceptions
 * when the input is invalid, incomplete, or improperly formatted. It ensures that the input
 * provided by the user meets the expected format for each task type and command.
 * </p>
 * <p>
 * This class is a key component of the application, facilitating user interaction by
 * interpreting input and guiding the flow of the program accordingly.
 * </p>
 */
public class Parser {

    /**
     * Parses the user's input to determine the command.
     * This method takes the input string provided by the user and attempts to
     * map it to a {@code Command} enum constant.
     *
     * @param userInput the input string from the user
     * @return the {@code Command} enum constant corresponding to the user's input
     * @see Command#fromString(String)
     */
    public Command parseCommand(String userInput) {
        return Command.fromString(userInput);
    }

    /**
     * Parses the user input and returns an array containing the task indices.
     * This method splits the input string by spaces and returns the arguments
     * starting from the second position onward, excluding the command keyword.
     *
     * For example, if the input is "mark 1 2 3", this method will return
     * an array containing ["1", "2", "3"].
     *
     * @param userInput the raw input string from the user, where the first argument
     *                  is the command, and the remaining arguments are task indices.
     * @return an array of strings containing the task indices extracted from the input.
     */
    public String[] parseTaskIndices(String userInput) {
        String[] args = userInput.split(" ");
        return Arrays.copyOfRange(args, 1, args.length);
    }


    /**
     * Parses the "finddate" or "findkey" command and extracts the search criteria from the user input.
     *
     * @param userInput The input string from the user, expected to start with "searchdate " or "find ".
     * @param tasks The {@code TaskList} containing the tasks to search.
     * @return An {@code ArrayList} of {@code Task} objects that match the search criteria.
     * @throws InvalidDateException if the argument is invalid as a date format.
     * @throws InvalidInputException if the argument is invalid and cannot be parsed as a keyword.
     */
    public ArrayList<Task> parseFindCommand(String userInput, TaskList tasks)
            throws InvalidDateException, InvalidInputException {
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2) {
            throw new InvalidInputException();
        }
        String command = parts[0];
        String argument = parts[1].trim(); // Extract the argument after "searchdate " or "find "
        if (command.equalsIgnoreCase("searchdate")) {
            try {
                LocalDate date = LocalDate.parse(argument);
                return tasks.findTasksByDate(date);
            } catch (DateTimeParseException e) {
                throw new InvalidDateException();
            }
        } else if (command.equalsIgnoreCase("find")) {
            return tasks.findTasksByKeyword(argument);
        } else {
            throw new InvalidInputException();
        }
    }

    /**
     * Parses the user's input to create a specific type of {@code Task}.
     * This method interprets the input string provided by the user and creates
     * an appropriate {@code Task} object (e.g., {@code Todo}, {@code Deadline}, or {@code Event}).
     * It throws specific exceptions if the input is malformed or missing necessary parts.
     *
     * @param userInput the input string from the user describing the task
     * @return a {@code Task} object that represents the user's input
     * @throws BobbyException if the user's input is invalid or incomplete, causing
     *                        one of several possible exceptions:
     *                <ul>
     *                  <li>{@code EmptyTodoException} if the description for a "todo" is empty.</li>
     *                  <li>{@code EmptyDeadlineException} if the "deadline" input is missing parts or malformed.</li>
     *                  <li>{@code EmptyEventException} if the "event" input is missing parts or malformed.</li>
     *                  <li>{@code InvalidDateException} if the date format is incorrect.</li>
     *                  <li>{@code InvalidInputException} if the input does not match any recognized command.</li>
     *                </ul>
     */
    public Task parseTask(String userInput) throws BobbyException {
        if (userInput.startsWith("todo ")) {
            String description = userInput.substring(5).trim();
            if (description.isEmpty()) {
                throw new EmptyTodoException();
            }
            return new Todo(description);
        } else if (userInput.startsWith("deadline ")) {
            String[] parts = userInput.substring(9).split(" /by ");
            if (parts.length < 2) {
                throw new EmptyDeadlineException();
            }
            String description = parts[0];
            String byString = parts[1];
            try {
                LocalDate by = LocalDate.parse(byString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return new Deadline(description, by);
            } catch (DateTimeParseException e) {
                throw new InvalidDateException();
            }
        } else if (userInput.startsWith("event ")) {
            String[] parts = userInput.substring(6).split(" /from | /to ");
            if (parts.length < 3) {
                throw new EmptyEventException();
            }
            String description = parts[0];
            String fromString = parts[1];
            String toString = parts[2];
            try {
                LocalDate from = LocalDate.parse(fromString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate to = LocalDate.parse(toString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return new Event(description, from, to);
            } catch (DateTimeParseException e) {
                throw new InvalidDateException();
            }
        } else {
            throw new InvalidInputException();
        }
    }
}
