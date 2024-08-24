package bobbybot;

import bobbybot.commands.Command;
import bobbybot.commands.CommandBye;
import bobbybot.commands.CommandDelete;
import bobbybot.commands.CommandList;
import bobbybot.commands.CommandTodo;
import bobbybot.commands.CommandEvent;
import bobbybot.commands.CommandDeadline;
import bobbybot.commands.CommandMark;
import bobbybot.commands.CommandUnmark;
import bobbybot.commands.CommandFind;

public class Parser {
    public static Command parse(String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        String command = inputArray[0].trim();
        String argument = inputArray.length > 1 ? inputArray[1].trim() : "";
        switch (command) {
        case "bye":
            return new CommandBye(argument);
        case "list":
            return new CommandList(argument);
        case "mark":
            return new CommandMark(argument);
        case "unmark":
            return new CommandUnmark(argument);
        case "delete":
            return new CommandDelete(argument);
        case "todo":
            return new CommandTodo(argument);
        case "deadline":
            return new CommandDeadline(argument);
        case "event":
            return new CommandEvent(argument);
        case "find":
            return new CommandFind(argument);
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
