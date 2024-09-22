package spongebob;

import spongebob.command.*;

/**
 * Object to parse user input into commands that can be executed by Spongebob
 */
public class Parser {

    private String[] arguments;

    /**
     * The command syntax is a array of size 4, first string will be the command,
     * second is description, third is from / by date, last is the to date
     */
    public Parser() {
        this.arguments = new String[4];

    }

    /**
     * Parses command into a Command object to be executed
     * @param string User input for commands
     * @return A executable command
     */
    public Command parse(String string) {

        for (int i = 0; i < arguments.length; i++) {
            this.arguments[i] = " ";
        }

        // search for command
        String[] commands = string.split(" ", 2);
        this.arguments[0] = commands[0].trim();
        if (commands.length > 1) {
            this.arguments[1] = commands[1].trim();
        }

        switch (this.arguments[0]) {
        case "list":
            return new DisplayCommand();

        case "mark": case "unmark":
            return new MarkCommand(arguments);

        case "todo":
            return new AddCommand(arguments);

        case "deadline":
            commands = arguments[1].split("/by", 2);

            if (commands.length > 1) {
                arguments[2] = commands[1].trim();
                arguments[1] = commands[0].trim();
            }
            return new AddCommand(arguments);

        case "event":
            commands = arguments[1].split("/from", 3);

            if (commands.length > 1) {
                arguments[1] = commands[0].trim();
                arguments[2] = commands[1].trim();
            }

            commands = arguments[2].split("/to", 2);

            if (commands.length > 1) {
                arguments[3] = commands[1].trim();
                arguments[2] = commands[0].trim();
            }

            return new AddCommand(arguments);

        case "delete":
            return new DeleteCommand(arguments);

        case "bye":
            return new ExitCommand();

        case "find":
            return new FindCommand(arguments);

        case "tag":
            commands = arguments[1].split(" ", 2);
            if (commands.length > 1) {
                arguments[2] = commands[1].trim();
                arguments[1] = commands[0].trim();
            }
            return new TagCommand(arguments);

        default:
            return new UnknownCommand();

        }

    }
}
