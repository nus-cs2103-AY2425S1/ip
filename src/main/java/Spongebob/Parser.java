package Spongebob;

import Spongebob.command.*;

/**
 * object to parse user input into commands that can be executed by Spongebob
 */
public class Parser {

    String[] arguments;

    /**
     * the command syntax is a array of size 4,
     * first string will be the command,
     * second is description,
     * third is from / by date,
     * last is the to date
     */
    public Parser() {
        this.arguments = new String[4];

        for (int i = 0; i < arguments.length; i++) {
            this.arguments[i] = " ";
        }
    }

    /**
     * Parses command into a Command object to be executed
     * @param string User input for commands
     * @return A executable command
     */
    public Command parse(String string) {

        String[] command = string.split(" ", 2);
        this.arguments[0] = command[0].trim();
        if (command.length > 1) {
            this.arguments[1] = command[1].trim();
        }

        command = arguments[1].split("/by", 2);
        if (command.length > 1) {
            arguments[2] = command[1].trim();
            arguments[1] = command[0].trim();

        } else {
            command = arguments[1].split("/from", 3);
            if (command.length > 1) {
                arguments[1] = command[0].trim();
                arguments[2] = command[1].trim();

                command = arguments[2].split("/to", 2);
                if (command.length > 1) {
                    arguments[3] = command[1].trim();
                    arguments[2] = command[0].trim();
                }
            }
        }

        switch (this.arguments[0]) {
            case "list":
                return new DisplayCommand();

            case "mark": case "unmark":
                return new MarkCommand(arguments);

            case "todo": case "deadline": case "event":
                return new AddCommand(arguments);

            case "delete":
                return new DeleteCommand(arguments);

            case "bye":
                return new ExitCommand();

            default:
                return new UnknownCommand();

        }


    }
}
