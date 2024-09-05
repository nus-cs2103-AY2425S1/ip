package parser;

import exception.KukiShinobuException;

// Import all the Commands
import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddTodoCommand;
import command.Command;
import command.DeleteTaskCommand;
import command.ExitCommand;
import command.ListCommand;
import command.MarkTaskCommand;
import command.UnknownCommand;
import command.UnmarkTaskCommand;


public class Parser {
    public static Command parse(String fullCommand) {
        // split user input into commands and argument (if applicable)
        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0];

        //prevents index out of bounds error if no arguments provided after command
        String arguments = parts.length > 1 ? parts[1] : "";
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
