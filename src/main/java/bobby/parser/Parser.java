package bobby.parser;

import bobby.command.Command;
import bobby.exceptions.*;
import bobby.tasks.Deadline;
import bobby.tasks.Event;
import bobby.tasks.Task;
import bobby.tasks.Todo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


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
     * Parses the user's input to create a specific type of {@code Task}.
     * This method interprets the input string provided by the user and creates
     * an appropriate {@code Task} object (e.g., {@code Todo}, {@code Deadline}, or {@code Event}).
     * It throws specific exceptions if the input is malformed or missing necessary parts.
     *
     * @param userInput the input string from the user describing the task
     * @return a {@code Task} object that represents the user's input
     * @throws BobbyException if the user's input is invalid or incomplete, causing
     *                        one of several possible exceptions:
     *                        <ul>
     *                          <li>{@code EmptyTodoException} if the description for a "todo" is empty.</li>
     *                          <li>{@code EmptyDeadlineException} if the "deadline" input is missing parts or malformed.</li>
     *                          <li>{@code EmptyEventException} if the "event" input is missing parts or malformed.</li>
     *                          <li>{@code InvalidDateException} if the date format is incorrect.</li>
     *                          <li>{@code InvalidInputException} if the input does not match any recognized command.</li>
     *                        </ul>
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
