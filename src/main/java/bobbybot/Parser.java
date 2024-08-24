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

/**
 * Represents a parser that parses user input, translating it to a Command.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command.
     *
     * @param input User input.
     * @return Command corresponding to the user input.
     * @throws BobbyBotException If the user input is invalid.
     */
    public static Command parse(String input) throws BobbyBotException {
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
        default:
            throw new BobbyBotException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
