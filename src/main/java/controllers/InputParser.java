package controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import controllers.commands.*;
import controllers.errors.*;
import models.Deadline;
import models.Event;
import models.Todo;

/**
 * The {@code InputParser} class is responsible for parsing user input and converting
 * it into executable {@code Command} objects for the task management system.
 * This class handles various commands such as adding tasks, marking tasks, deleting tasks,
 * and exiting the program.
 *
 * <p>It identifies the type of command and creates the appropriate command object,
 * throwing errors if the input is invalid or the command doesn't exist.</p>
 */
public class InputParser {

    /**
     * Parses the given user input and converts it into an executable {@code Command}.
     * Based on the input, this method returns the appropriate command for execution,
     * or throws an error if the input is invalid or unrecognized.
     *
     * @param cmd The string input provided by the user.
     * @return The appropriate {@code Command} object to execute.
     * @throws InvalidInputError If the input is malformed or incomplete.
     * @throws InvalidCommandError If the command does not exist.
     */
    public Command parse(String cmd) throws InvalidInputError, InvalidCommandError {

        if (isByeCommand(cmd)) {
            return new ByeCommand();
        } else if (isMarkCommand(cmd)) {
            try {
                String[] parts = cmd.split(" ");
                int index = Integer.parseInt(parts[1]);
                return new MarkTaskCommand(index);
            } catch (Exception e) {
                throw new InvalidInputError("Invalid input for mark!");
            }
        } else if (isListCommand(cmd)) {
            return new ListCommand();
        } else if (isDeleteCommand(cmd)) {
            try {
                String[] parts = cmd.split(" ");
                int index = Integer.parseInt(parts[1]);
                return new DeleteTaskCommand(index);
            } catch (Exception e) {
                throw new InvalidInputError("Invalid input for delete!");
            }
        } else if (isUnmarkCommand(cmd)) {
            try {
                String[] parts = cmd.split(" ");
                int index = Integer.parseInt(parts[1]);
                return new UnmarkTaskCommand(index);
            } catch (Exception e) {
                throw new InvalidInputError("Invalid input for unmark!");
            }
        } else if (isTodoCommand(cmd)) {
            try {
                Todo newTask = new Todo(cmd.substring(5));
                return new AddTodoCommand(newTask);
            } catch (Exception e) {
                throw new InvalidInputError("Invalid input for todo!");
            }

        } else if (isDeadlineCommand(cmd)) {
            try {
                String description = extractStringBetweenTwoSubStrings(cmd, "deadline", "/by");
                String by = extractStringFromSubstringToEnd(cmd, "/by");
                Deadline deadline = new Deadline(description, by);
                return new AddDeadlineCommand(deadline);
            } catch (Exception e) {
                throw new InvalidInputError("Invalid input for deadline!");
            }

        } else if (isEventCommand(cmd)) {
            try {
                String description = extractStringBetweenTwoSubStrings(cmd, "event", "/from");
                String from = extractStringBetweenTwoSubStrings(cmd, "/from", "/to");
                String to = extractStringFromSubstringToEnd(cmd, "/to");
                Event event = new Event(description, from, to);
                return new AddEventCommand(event);
            } catch (Exception e) {
                throw new InvalidInputError("Invalid input for event!");
            }
        } else {
            String errorString = String.format("%s doesn't exist as a command", cmd);
            throw new InvalidCommandError(errorString);
        }
    }
    /**
     * Checks if the input string is a valid "mark" command.
     *
     * @param command The input string to check.
     * @return {@code true} if the input is a valid "mark" command, {@code false} otherwise.
     */
    private boolean isMarkCommand(String command) {
        String markRegex = "mark (100|[1-9]|[1-9][0-9])";
        Pattern markPattern = Pattern.compile(markRegex);
        Matcher markMatcher = markPattern.matcher(command);
        return markMatcher.matches();
    }
    /**
     * Checks if the input string is a valid "unmark" command.
     *
     * @param command The input string to check.
     * @return {@code true} if the input is a valid "unmark" command, {@code false} otherwise.
     */
    private boolean isUnmarkCommand(String command) {
        String unmarkRegex = "unmark (100|[1-9]|[1-9][0-9])";
        Pattern unmarkPattern = Pattern.compile(unmarkRegex);
        Matcher unmarkMatcher = unmarkPattern.matcher(command);
        return unmarkMatcher.matches();
    }
    /**
     * Checks if the input string is a "list" command.
     *
     * @param command The input string to check.
     * @return {@code true} if the input is a "list" command, {@code false} otherwise.
     */
    private boolean isListCommand(String command) {
        return command.equals("list");
    }
    /**
     * Checks if the input string is a "bye" command.
     *
     * @param command The input string to check.
     * @return {@code true} if the input is a "bye" command, {@code false} otherwise.
     */
    private boolean isByeCommand(String command) {
        return command.equals("bye");
    }
    /**
     * Checks if the input string is a "todo" command.
     *
     * @param command The input string to check.
     * @return {@code true} if the input is a "todo" command, {@code false} otherwise.
     */
    private boolean isTodoCommand(String command) {
        String[] parts = command.split(" ");
        return parts[0].equals("todo");
    }
    /**
     * Checks if the input string is a "deadline" command.
     *
     * @param command The input string to check.
     * @return {@code true} if the input is a "deadline" command, {@code false} otherwise.
     */
    private boolean isDeadlineCommand(String command) {
        String[] parts = command.split(" ");
        return parts[0].equals("deadline");
    }
    /**
     * Checks if the input string is an "event" command.
     *
     * @param command The input string to check.
     * @return {@code true} if the input is an "event" command, {@code false} otherwise.
     */
    private boolean isEventCommand(String command) {
        String[] parts = command.split(" ");
        return parts[0].equals("event");
    }
    /**
     * Checks if the input string is a valid "delete" command.
     *
     * @param command The input string to check.
     * @return {@code true} if the input is a valid "delete" command, {@code false} otherwise.
     */
    private boolean isDeleteCommand(String command) {
        String deleteRegex = "delete (100|[1-9]|[1-9][0-9])";
        Pattern deletePattern = Pattern.compile(deleteRegex);
        Matcher deleteMatcher = deletePattern.matcher(command);
        return deleteMatcher.matches();
    }
    /**
     * Extracts the substring between two specified substrings in the input command.
     *
     * @param command The input command string.
     * @param prefix The prefix marking the start of the substring.
     * @param byMarker The marker marking the end of the substring.
     * @return The extracted substring.
     */
    private String extractStringBetweenTwoSubStrings(String command, String prefix, String byMarker) {
        int taskStartIndex = command.indexOf(prefix) + prefix.length();
        int byIndex = command.indexOf(byMarker);

        return command.substring(taskStartIndex+1, byIndex);
    }
    /**
     * Extracts the substring from the specified prefix to the end of the input command.
     *
     * @param command The input command string.
     * @param prefix The prefix marking the start of the substring.
     * @return The extracted substring.
     */
    private String extractStringFromSubstringToEnd(String command, String prefix) {
        int taskStartIndex = command.indexOf(prefix) + prefix.length();
        return command.substring(taskStartIndex+1);
    }
}