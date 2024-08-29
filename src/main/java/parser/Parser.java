package parser;

import command.*;
import command.AddEventCommand;
import exception.KukiShinobuException;

public class Parser {

    // TODO: Create a method that takes in a command string and returns a command
    public static Command parse(String fullCommand) {
        // split user input into commands and argument (if applicable)
        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0];
        //prevents index out of bounds error if no arguments provided after command
        String arguments = parts.length > 1 ? parts[1] : "";

        // break out of while loop if user issues "bye" command


        try {
            // otherwise, handle all other commands as appropriate
            switch (command) {
                case "bye":
                    return new ExitCommand();
                case "list":
                    return new ListCommand();
                case "mark":
                    // argument is task index
                    return new MarkTaskCommand(arguments);
                case "unmark":
                    // argument is task index
                    return new UnmarkTaskCommand(arguments);
                case "delete":
                    // argument is task index
                    return new DeleteTaskCommand(arguments);
                case "todo":
                    // argument is desc, pass desc in
                    return new AddTodoCommand(arguments);
                case "deadline":
                    // Break arguments into desc + by
                    return new AddDeadlineCommand(arguments);
                case "event":
                    // break arguments into desc, start and end
                    return new AddEventCommand(arguments);
                default:
                    return new UnknownCommand();
            }
        } catch (KukiShinobuException e) {
            System.out.println(e.getMessage());
        }
        return new UnknownCommand();
    }

}
