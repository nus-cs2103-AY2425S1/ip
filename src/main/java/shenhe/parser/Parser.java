package shenhe.parser;

import java.time.LocalDateTime;
import java.util.Objects;

import shenhe.command.Command;
import shenhe.command.DeadlineCommand;
import shenhe.command.DeleteCommand;
import shenhe.command.EventCommand;
import shenhe.command.ExitCommand;
import shenhe.command.FindCommand;
import shenhe.command.ListCommand;
import shenhe.command.MarkCommand;
import shenhe.command.TodoCommand;
import shenhe.command.UnmarkCommand;
import shenhe.exception.EmptyTaskDescriptionException;
import shenhe.exception.InvalidListEnquiry;
import shenhe.exception.UnknownTaskException;
import shenhe.task.Deadline;
import shenhe.task.Event;
import shenhe.task.Task;
import shenhe.task.Todo;


/**
 * The {@code Parser} class is responsible for interpreting user input and converting it into executable commands.
 * It also handles the parsing of saved task data from file lines into {@code Task} objects.
 */
public class Parser {

    /**
     * Parses a line from the save file and converts it into a {@code Task} object.
     * The line format determines the type of task (Todo, Deadline, Event) and its attributes.
     *
     * @param line A string representing a task in the save file.
     * @return A {@code Task} object corresponding to the parsed line,
     *          or {@code null} if parsing fails or the task type is unknown.
     */
    public static Task parseCommandInFile(String line) {
        // Check the first character to determine the task type
        if (line.startsWith("T")) {
            // Assume the format is "T | isDone | description"
            String[] parts = line.split(" \\| ");

            // Assert that the task string has the correct number of parts for a Todo
            assert parts.length == 3 : "Todo task string should have 3 parts but found " + parts.length;

            boolean isDone = Objects.equals(parts[1], "1");
            return new Todo(parts[2], isDone); // Adjust constructor as per your shenhe.task.Todo class definition
        } else if (line.startsWith("D")) {
            // Assume the format is "D | isDone | description | by"
            String[] parts = line.split(" \\| ");

            // Assert that the task string has the correct number of parts for a Deadline
            assert parts.length == 4 : "Deadline task string should have 4 parts but found " + parts.length;

            boolean isDone = parts[1].equals("1");
            try {
                LocalDateTime by = DateParser.parseDateTimeFromFile(parts[3]); // Parse date string
                return new Deadline(parts[2], isDone, by);
            } catch (Exception e) {
                System.out.println("Invalid date/time format.");
                return null; // Return null if parsing fails
            }
        } else if (line.startsWith("E")) {
            // Assume the format is "E | isDone | description | from | to"
            String[] parts = line.split(" \\| ");

            // Assert that the task string has the correct number of parts for an Event
            assert parts.length == 5 : "Event task string should have 5 parts but found " + parts.length;

            boolean isDone = parts[1].equals("1");
            try {
                String from = parts[3]; // Parse "from" date string
                String to = parts[4]; // Parse "to" date string
                return new Event(parts[2], isDone, from, to);
            } catch (Exception e) {
                System.out.println("Invalid date/time format.");
                return null; // Return null if parsing fails
            }
        }

        // Handle unknown types by returning null
        return null;
    }


    /**
     * Parses user input and returns the corresponding {@code Command} object.
     * This method interprets the user's command and creates the appropriate command instance.
     *
     * @param userInput The user input string containing the command.
     * @return A {@code Command} object that corresponds to the user input.
     * @throws Exception Various exceptions that correspond to invalid commands or task descriptions:
     *                   {@link EmptyTaskDescriptionException} if the task description is empty,
     *                   {@link InvalidListEnquiry} if the list command is invalid,
     *                   {@link UnknownTaskException} if the command is not recognized.
     */
    public static Command parse(String userInput) throws Exception {
        if (userInput.equals("bye")) {
            return new ExitCommand();
        } else if (userInput.startsWith("mark")) {
            return new MarkCommand(userInput);
        } else if (userInput.startsWith("unmark")) {
            return new UnmarkCommand(userInput);
        } else if (userInput.startsWith("delete")) {
            return new DeleteCommand(userInput);
        } else if (userInput.startsWith(("todo"))) {
            if (userInput.trim().length() == 4) {
                throw new EmptyTaskDescriptionException();
            }
            return new TodoCommand(userInput);
        } else if (userInput.startsWith("deadline")) {
            if (userInput.trim().length() == 8) {
                throw new EmptyTaskDescriptionException();
            }
            return new DeadlineCommand(userInput);
        } else if (userInput.startsWith("event")) {
            if (userInput.trim().length() == 5) {
                throw new EmptyTaskDescriptionException();
            }
            return new EventCommand(userInput);
        } else if (userInput.startsWith("list")) {
            if (userInput.trim().length() != 4) {
                throw new InvalidListEnquiry();
            }
            return new ListCommand(userInput);
        } else if (userInput.startsWith("find")) {
            if (userInput.trim().length() == 4) {
                throw new EmptyTaskDescriptionException();
            }
            return new FindCommand(userInput);
        }
        throw new UnknownTaskException();
    }
}
