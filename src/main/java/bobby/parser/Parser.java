package bobby.parser;

import bobby.command.Command;
import bobby.exceptions.*;
import bobby.tasklist.TaskList;
import bobby.tasks.Deadline;
import bobby.tasks.Event;
import bobby.tasks.Task;
import bobby.tasks.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {

    public Command parseCommand(String userInput) {
        return Command.fromString(userInput);
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
    public ArrayList<Task> parseFindCommand(String userInput, TaskList tasks) throws InvalidDateException, InvalidInputException {
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
