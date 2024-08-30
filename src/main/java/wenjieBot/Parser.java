package wenjieBot;

import wenjieBot.commands.*;
import wenjieBot.exceptions.UnknownCommandException;


public class Parser {
    public static Command parse(String input) throws UnknownCommandException {
        String[] parts = input.split(" ");
        String action = parts[0].toLowerCase();
        Command response = null;

        switch (action) {
            case "mark": {
                response = new MarkCommand(false, input);
                break;
            }

            case "unmark": {
                response = new UnmarkCommand(false, input);
                break;
            }

            case "delete": {
                response = new DeleteCommand(false, input);
                break;
            }

            case "bye": {
                response = new ByeCommand(true, input);
                break;
            }

            case "list": {
                response = new ListCommand(false, input);
                break;
            }

            case "todo": {
                response = new AddCommand(false, input, AddCommand.TypeOfEvent.TODO);
                break;
            }

            case "event": {
                response = new AddCommand(false, input, AddCommand.TypeOfEvent.EVENT);
                break;
            }

            case "deadline": {
                response = new AddCommand(false, input, AddCommand.TypeOfEvent.DEADLINE);
                break;
            }

            case "find": {
                response = new FindCommand(false, input);
                break;
            }

            default: {
                throw new UnknownCommandException();
            }
        }

        return response;

    }
}
