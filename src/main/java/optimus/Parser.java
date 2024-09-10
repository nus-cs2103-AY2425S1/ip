package optimus;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Optional;

import optimus.commands.AddTaskCommand;
import optimus.commands.Command;
import optimus.commands.DeleteTaskCommand;
import optimus.commands.FindCommand;
import optimus.commands.LeaveCommand;
import optimus.commands.ListCommand;
import optimus.commands.MarkCommand;
import optimus.commands.UnmarkCommand;
import optimus.exceptions.IncompleteCommandException;
import optimus.exceptions.InvalidCommandException;
import optimus.exceptions.InvalidDateFormatException;
import optimus.exceptions.InvalidTaskNumberException;
import optimus.exceptions.OptimusExceptions;
import optimus.tasks.DeadlineTask;
import optimus.tasks.EventTask;
import optimus.tasks.ToDoTask;

/**
 * User input reader
 */
public class Parser {

    private static final String[] FLAGS = {"/from", "/to", "/by"};
    private static final String TASK_NOT_SPECIFIED_MSG = "The task number is not specified";
    private static final String NO_DESCRIPTION_MSG = "This task requires a description";

    /**
     * Returns a Command object based on user input and throws an Exception for invalid commands
     *
     * @param input - user input through keyboard
     * @return corresponding Command object
     * @throws OptimusExceptions - thrown for incorrect or incomplete commands
     */
    public static Command parse(String input) throws OptimusExceptions {
        String[] commands = input.split(" ");
        if (commands.length == 0) {
            throw new InvalidCommandException("No command entered.");
        }

        String command = commands[0].toLowerCase();
        return switch (command) {
        case "bye" -> new LeaveCommand();
        case "list" -> new ListCommand();
        case "mark" -> new MarkCommand(parseTaskNumber(commands));
        case "unmark" -> new UnmarkCommand(parseTaskNumber(commands));
        case "todo", "deadline", "event" -> addTask(commands);
        case "delete" -> new DeleteTaskCommand(parseTaskNumber(commands));
        case "find" -> new FindCommand(extractDescription(commands));
        default -> throw new InvalidCommandException("This command does not exist.");
        };
    }

    private static int parseTaskNumber(String[] commands) throws OptimusExceptions {
        if (commands.length != 2) {
            throw new IncompleteCommandException(TASK_NOT_SPECIFIED_MSG);
        }
        try {
            int taskNum = Integer.parseInt(commands[1]);
            if (taskNum <= 0) {
                throw new InvalidTaskNumberException();
            }
            return taskNum - 1;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException("Invalid number format: " + e.getMessage());
        }
    }

    /**
     * Returns an appropriate AddTaskCommand based on the command
     *
     * @param commands - commands as an array of Strings, each word of the command must be a separate element
     * @return AddTaskCommand with the correct Task as its input
     * @throws IncompleteCommandException - Thrown when commands lack dates or time
     * @throws InvalidCommandException    - Thrown if the method is called incorrectly
     * @throws InvalidDateFormatException - Thrown if the date format for the deadline task is incorrect
     */
    private static Command addTask(String[] commands) throws IncompleteCommandException,
            InvalidCommandException, InvalidDateFormatException {
        if (commands.length < 2) {
            throw new IncompleteCommandException(NO_DESCRIPTION_MSG);
        }

        String type = commands[0];
        String description = extractDescription(commands);
        String startTime;
        String endTime;

        switch (type) {
        case "todo" -> {
            return new AddTaskCommand(new ToDoTask(description));
        }
        case "deadline" -> {
            endTime = extractDateAfterFlag(commands, "/by")
                    .orElseThrow(() -> new IncompleteCommandException("Deadline Tasks must have a deadline specified"));
            LocalDate deadline;
            try {
                deadline = LocalDate.parse(endTime);
            } catch (DateTimeParseException e) {
                throw new InvalidDateFormatException("Dates for Deadline tasks must be in the YYYY-MM-DD format");
            }
            return new AddTaskCommand(new DeadlineTask(description, deadline));
        }
        case "event" -> {
            startTime = extractDateAfterFlag(commands, "/from")
                    .orElseThrow(() -> new IncompleteCommandException("Events must have a start specified"));
            endTime = extractDateAfterFlag(commands, "/to")
                    .orElseThrow(() -> new IncompleteCommandException("Events must have an end specified"));
            return new AddTaskCommand(new EventTask(description, startTime, endTime));
        }
        default -> throw new InvalidCommandException("This task does not exist.");
        }
    }

    private static String extractDescription(String[] commands) throws IncompleteCommandException {
        int endIndex = findFirstFlagIndex(commands).orElse(commands.length);
        if (endIndex <= 1) {
            throw new IncompleteCommandException("Command needs a description");
        }
        return String.join(" ", Arrays.copyOfRange(commands, 1, endIndex));
    }

    private static Optional<Integer> findFirstFlagIndex(String[] commands) {
        for (int i = 1; i < commands.length; i++) {
            if (Arrays.asList(FLAGS).contains(commands[i])) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    private static Optional<String> extractDateAfterFlag(String[] commands, String flag) {
        int index = Arrays.asList(commands).indexOf(flag);
        if (index == -1 || index + 1 >= commands.length) {
            return Optional.empty();
        }

        int endIndex = findFirstFlagIndex(Arrays.copyOfRange(commands, index + 1, commands.length))
                .map(i -> i + index + 1)
                .orElse(commands.length);

        return Optional.of(String.join(" ", Arrays.copyOfRange(commands, index + 1, endIndex)).trim());
    }
}
