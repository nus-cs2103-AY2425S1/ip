package controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import controllers.commands.*;
import models.Task;
import models.Deadline;
import models.Event;
import models.Todo;

public class InputParser {

    public Command parse(String cmd) {

        if (isByeCommand(cmd)) {
            return new ByeCommand();
        } else if (isMarkCommand(cmd)) {
            String[] parts = cmd.split(" ");
            int index = Integer.parseInt(parts[1]);
            return new MarkTaskCommand(index);
        } else if (isListCommand(cmd)) {
            return new ListCommand();
        } else if (isUnmarkCommand(cmd)) {
            String[] parts = cmd.split(" ");
            int index = Integer.parseInt(parts[1]);
            return new UnmarkTaskCommand(index);
        } else if (isTodoCommand(cmd)) {
            Todo newTask = new Todo(cmd.substring(5));
            return new AddTodoCommand(newTask);
        } else if (isDeadlineCommand(cmd)) {
            String description = extractStringBetweenTwoSubStrings(cmd, "deadline", "/by");
            String by = extractStringFromSubstringToEnd(cmd, "/by");
            Deadline deadline = new Deadline(description, by);
            return new AddDeadlineCommand(deadline);
        } else if (isEventCommand(cmd)) {
            String description = extractStringBetweenTwoSubStrings(cmd, "event", "/from");
            String from = extractStringBetweenTwoSubStrings(cmd, "/from", "/to");
            String to = extractStringFromSubstringToEnd(cmd, "/to");
            Event event = new Event(description, from, to);
            return new AddEventCommand(event);
        } else {
            Task newTask = new Task(cmd);
            return new AddTaskCommand(newTask);
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

    private boolean isEventCommand(String command) {
        String[] parts = command.split(" ");
        return parts[0].equals("event");
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