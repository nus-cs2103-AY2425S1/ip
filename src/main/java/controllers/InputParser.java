package controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import controllers.commands.*;
import controllers.errors.*;
import models.Deadline;
import models.Event;
import models.Todo;

public class InputParser {

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

        } else if (isFindCommand(cmd)) {
            try {
                String[] parts = cmd.split(" ");
                String taskName = parts[1];
                return new FindCommand(taskName);
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

    private boolean isMarkCommand(String command) {
        String markRegex = "mark (100|[1-9]|[1-9][0-9])";
        Pattern markPattern = Pattern.compile(markRegex);
        Matcher markMatcher = markPattern.matcher(command);
        return markMatcher.matches();
    }

    private boolean isUnmarkCommand(String command) {
        String unmarkRegex = "unmark (100|[1-9]|[1-9][0-9])";
        Pattern unmarkPattern = Pattern.compile(unmarkRegex);
        Matcher unmarkMatcher = unmarkPattern.matcher(command);
        return unmarkMatcher.matches();
    }

    private boolean isListCommand(String command) {
        return command.equals("list");
    }

    private boolean isByeCommand(String command) {
        return command.equals("bye");
    }

    private boolean isTodoCommand(String command) {
        String[] parts = command.split(" ");
        return parts[0].equals("todo");
    }

    private boolean isDeadlineCommand(String command) {
        String[] parts = command.split(" ");
        return parts[0].equals("deadline");
    }

<<<<<<< Updated upstream
=======

    private boolean isFindCommand(String command) {
        String[] parts = command.split(" ");
        return parts[0].equals("find");
    }
    /**
     * Checks if the input string is an "event" command.
     *
     * @param command The input string to check.
     * @return {@code true} if the input is an "event" command, {@code false} otherwise.
     */
>>>>>>> Stashed changes
    private boolean isEventCommand(String command) {
        String[] parts = command.split(" ");
        return parts[0].equals("event");
    }

    private boolean isDeleteCommand(String command) {
        String deleteRegex = "delete (100|[1-9]|[1-9][0-9])";
        Pattern deletePattern = Pattern.compile(deleteRegex);
        Matcher deleteMatcher = deletePattern.matcher(command);
        return deleteMatcher.matches();
    }

    private String extractStringBetweenTwoSubStrings(String command, String prefix, String byMarker) {
        int taskStartIndex = command.indexOf(prefix) + prefix.length();
        int byIndex = command.indexOf(byMarker);

        return command.substring(taskStartIndex+1, byIndex);
    }

    private String extractStringFromSubstringToEnd(String command, String prefix) {
        int taskStartIndex = command.indexOf(prefix) + prefix.length();
        return command.substring(taskStartIndex+1);
    }
}