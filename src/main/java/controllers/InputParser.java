package controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import controllers.commands.*;
import models.Task;

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
}