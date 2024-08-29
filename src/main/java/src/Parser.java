package src;

import src.commands.Command;
import src.commands.MarkCommand;
import src.commands.DeleteCommand;
import src.commands.ByeCommand;
import src.commands.ListCommand;
import src.commands.UnmarkCommand;
import src.commands.AddCommand;


public class Parser {
    public static Command parse(String input) throws UnknownCommandException {
        String[] parts = input.split(" ");
        String action = parts[0].toLowerCase();
        Command response = null;

        switch (action) {
            case "mark": {
                response = new MarkCommand(true, input);
                break;
            }

            case "unmark": {
                response = new UnmarkCommand(true, input);
                break;
            }

            case "delete": {
                response = new DeleteCommand(true, input);
                break;
            }

            case "bye": {
                response = new ByeCommand(false, input);
                break;
            }

            case "list": {
                response = new ListCommand(true, input);
                break;
            }

            case "todo": {
                response = new AddCommand(true, input, AddCommand.TypeOfEvent.TODO);
                break;
            }

            case "event": {
                response = new AddCommand(true, input, AddCommand.TypeOfEvent.EVENT);
                break;
            }

            case "deadline": {
                response = new AddCommand(true, input, AddCommand.TypeOfEvent.DEADLINE);
                break;
            }

            default: {
                throw new UnknownCommandException();
            }
        }

        return response;

    }
}
