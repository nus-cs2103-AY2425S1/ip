package chatkaki;

import chatkaki.commands.*;

import java.util.Scanner;

/**
 * Represents a parser to parse user input.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param scanner The scanner to read user input.
     * @return The command corresponding to the user input.
     */
    public static Command parse(Scanner scanner) {
        String userInput = scanner.nextLine();
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
            default:
                return new CommandUnknown(inputs);
        }
    }







}
