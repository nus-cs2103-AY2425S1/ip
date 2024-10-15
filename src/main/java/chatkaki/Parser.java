package chatkaki;

import chatkaki.commands.Command;
import chatkaki.commands.CommandBye;
import chatkaki.commands.CommandDeadline;
import chatkaki.commands.CommandDelete;
import chatkaki.commands.CommandEvent;
import chatkaki.commands.CommandFind;
import chatkaki.commands.CommandList;
import chatkaki.commands.CommandMark;
import chatkaki.commands.CommandTodo;
import chatkaki.commands.CommandTypes;
import chatkaki.commands.CommandUnknown;
import chatkaki.commands.CommandUnmark;

/**
 * Represents a parser to parse user input.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param userInput The  user input.
     * @return The command corresponding to the user input.
     */
    @SuppressWarnings("checkstyle:Indentation")
    public static Command parse(String userInput) {
        String[] inputs = userInput.split(" ", 2);
        CommandTypes command = CommandTypes.fromString(inputs[0]);

        switch (command) {
            case DELETE:
                return new CommandDelete(inputs);
            case TODO:
                return new CommandTodo(inputs);
            case DEADLINE:
                return new CommandDeadline(inputs);
            case EVENT:
                return new CommandEvent(inputs);
            case BYE:
                return new CommandBye(inputs);
            case MARK:
                return new CommandMark(inputs);
            case UNMARK:
                return new CommandUnmark(inputs);
            case LIST:
                return new CommandList(inputs);
            case FIND:
                return new CommandFind(inputs);
            default:
                return new CommandUnknown(inputs);
        }
    }







}
