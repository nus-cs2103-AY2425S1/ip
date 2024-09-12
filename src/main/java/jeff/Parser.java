package jeff;


import jeff.command.Command;
import jeff.command.CommandFactory;
import jeff.exceptions.JeffException;

/**
 * Parses user input and returns the appropriate command.
 * The Parser class is responsible for interpreting user commands and returning the corresponding Command object.
 */
public class Parser {
    private static final CommandFactory commandFactory = new CommandFactory();
    /**
     * Parses the user's command input and returns the corresponding Command object.
     *
     * @param fullCommand The full command string input by the user.
     * @return The Command object corresponding to the user's input.
     * @throws JeffException If the command is not recognized.
     */
    public static Command parse(String fullCommand) throws JeffException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1] : "";
        return commandFactory.getCommand(commandWord, args);
    }
}
