package common;

import common.Command;
import commands.AddTodoCommand;
import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.EchoCommand;
import commands.ExitCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnmarkCommand;

public class Parser {
    public Command parse(String input) {

        String normalizedInput = input.trim().toLowerCase();

//        if (normalizedInput.equals("bye")) {
//            return new ExitCommand();
//        } else if (normalizedInput.equals("list")) {
//            return new ListCommand();
//        } else if (normalizedInput.startsWith("mark ")) {
//            String task = input.substring(5).trim();
//            return new MarkCommand(task);
//        } else if (normalizedInput.startsWith("unmark ")) {
//            String task = input.substring(7).trim();
//            return new UnmarkCommand(task);
//        } else {
//            return new EchoCommand(input);
//        }
        if (normalizedInput.equals("bye")) {
            return new ExitCommand();
        } else if (normalizedInput.equals("list")) {
            return new ListCommand();
        } else if (normalizedInput.startsWith("todo ")) {
            return new AddTodoCommand(input);
        } else if (normalizedInput.startsWith("deadline ")) {
            return new AddDeadlineCommand(input);
        } else if (normalizedInput.startsWith("event ")) {
            return new AddEventCommand(input);
        } else if (normalizedInput.startsWith("mark ")) {
            return new MarkCommand(input);
        } else if (normalizedInput.startsWith("unmark ")) {
            return new UnmarkCommand(input);
        } else {
            return new EchoCommand(input);
        }
    }
}