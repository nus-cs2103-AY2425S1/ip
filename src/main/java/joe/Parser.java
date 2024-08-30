package joe;

import joe.Commands;
import joe.JoeException;
import joe.command.*;

public class Parser {
    //parse the user commands

    /**
     * Parses the raw input by the user and returns a Command object to be executed.
     * @param rawInput The raw string input by the user.
     * @return A Command object that will be executed.
     */
    public static Command parse(String rawInput) {
        if (rawInput.isEmpty()) {
            throw new JoeException("\tOOPS! You did not enter anything");
        }
        String[] inputArr = rawInput.split(" ");
        String strCommand = inputArr[0].toUpperCase();
        Commands command = null;
        try {
            command = Commands.valueOf(strCommand);
        } catch (IllegalArgumentException e) {
            throw new JoeException("Unknown command");
        }
        switch (command) {
            case BYE -> {
                return new ExitCommand();
            }
            case LIST -> {
                return new ListCommand();
            }
            case TODO, DEADLINE, EVENT -> {
                // TODO
                return new AddCommand(command, inputArr);
            }
            case DELETE -> {
                return new DeleteCommand(inputArr);
            }
            case MARK,UNMARK -> {
                return new ToggleCommand(command, inputArr);
            }
            case FIND -> {
                if (inputArr.length != 2) {
                    throw new JoeException("Find currently only supports a single word query");
                }
                String query = inputArr[1];
                return new FindCommand(query);
            }
            default -> {
                return new UnknownCommand();
            }
        }
    }
}
