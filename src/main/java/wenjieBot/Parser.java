package wenjieBot;

import wenjieBot.commands.Command;
import wenjieBot.commands.MarkCommand;
import wenjieBot.commands.DeleteCommand;
import wenjieBot.commands.ByeCommand;
import wenjieBot.commands.ListCommand;
import wenjieBot.commands.UnmarkCommand;
import wenjieBot.commands.AddCommand;
import wenjieBot.exceptions.UnknownCommandException;


public class Parser {
    public static Command parse(String input) throws UnknownCommandException {
        String[] parts = input.split(" ");
        String action = parts[0].toLowerCase();

        switch (action){
        case "mark":
            return new MarkCommand(false, input);
            // Fallthrough


        case "unmark":
            return new UnmarkCommand(false, input);
            // Fallthrough


        case "delete":
            return new DeleteCommand(false, input);
            // Fallthrough


        case "bye":
            return new ByeCommand(true, input);
            // Fallthrough


        case "list":
            return new ListCommand(false, input);
            // Fallthrough


        case "todo":
            return new AddCommand(false, input, AddCommand.TypeOfEvent.TODO);
            // Fallthrough


        case "event":
            return new AddCommand(false, input, AddCommand.TypeOfEvent.EVENT);
            // Fallthrough


        case "deadline":
            return new AddCommand(false, input, AddCommand.TypeOfEvent.DEADLINE);
            // Fallthrough


        default:
            throw new UnknownCommandException();
        }

    }
}
