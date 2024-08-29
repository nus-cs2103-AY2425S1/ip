package bobby;

import bobby.exception.BobbyException;
import bobby.exception.UnknownCommandException;

public class Parser {

    public static Command parse(String userInput) throws UnknownCommandException {
        String[] splitInput = userInput.split(" ");
        if (splitInput[0].equalsIgnoreCase("bye")) {
            return Command.BYE;
        }
        if (splitInput[0].equalsIgnoreCase("list")) {
            return Command.LIST;
        }
        if (splitInput[0].equalsIgnoreCase("todo")) {
            return Command.TODO;
        }
        if (splitInput[0].equalsIgnoreCase("deadline")) {
            return Command.DEADLINE;
        }
        if (splitInput[0].equalsIgnoreCase("event")) {
            return Command.EVENT;
        }
        if (splitInput[0].equalsIgnoreCase("mark")) {
            return Command.MARK;
        }
        if (splitInput[0].equalsIgnoreCase("unmark")) {
            return Command.UNMARK;
        }
        if (splitInput[0].equalsIgnoreCase("delete")) {
            return Command.DELETE;
        }
        if (splitInput[0].equalsIgnoreCase("find")) {
            return Command.FIND;
        }
        throw new UnknownCommandException();
    }

    public static int parseNumber(String input, int commandLength) throws BobbyException {
        try {
            String inputWithoutCommand = input.substring(commandLength).trim();
            return Integer.parseInt(inputWithoutCommand);
        } catch (NumberFormatException e) {
            throw new BobbyException("My apologies. I cannot decipher what is that number!");
        }
    }
}
