package commands;

import exceptions.EchoException;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;

/**
 * Provides help information for EchoBot commands.
 */
public class HelpCommand {

    /**
     * Retrieves help information based on user input.
     *
     * @param userInput The user's input command.
     * @return A help message corresponding to the input command.
     * @throws EchoException If the command is invalid or not recognized.
     */
    public static String getHelp(String userInput) throws EchoException {
        if (userInput.equals("help")) {
            return getGeneralHelp();
        }

        if (!isValidCommand(userInput)) {
            throw new EchoException("Please enter a valid command if you need help");
        }

        return getCommandHelp(userInput);
    }

    /**
     * Checks if the given user input is a valid command.
     *
     * @param userInput The user's input command.
     * @return True if the command is valid, false otherwise.
     */
    public static boolean isValidCommand(String userInput) {
        String[] commandArray = userInput.split(" ");

        if (commandArray.length != 2) {
            return false;
        }

        if (commandArray.length == 2 && commandArray[1].equals("help")) {
            return false;
        }

        return true;
    }

    /**
     * Provides a general help message listing all available commands.
     *
     * @return A string containing a list of available commands.
     */
    public static String getGeneralHelp() {
        String message = "These are common EchoBot commands used:\n";
        for (Command command : Command.values()) {
            message += "\t" + command.name().toLowerCase() + "\n";
        }
        return message + "\nSee 'help [command]' to read about a specific command.";
    }

    /**
     * Provides a help message for a specific command.
     *
     * @param userInput The user's input command.
     * @return A help message for the specified command.
     * @throws EchoException If the command is invalid or not recognized.
     */
    public static String getCommandHelp(String userInput) throws EchoException {
        String command = userInput.split(" ")[1].toUpperCase();

        try {
            switch (Command.valueOf(command)) {
            case BYE:
                return ChatCommand.getHelpMessage();
            case HI:
                return ChatCommand.getHelpMessage();
            case LIST:
                return ListCommand.getHelpMessage();
            case MARK:
                return MarkCommand.getHelpMessage();
            case UNMARK:
                return UnmarkCommand.getHelpMessage();
            case TAG:
                return TagCommand.getHelpMessage();
            case DELETE:
                return DeleteCommand.getHelpMessage();
            case FIND:
                return FindCommand.getHelpMessage();
            case TODO:
                return ToDo.getHelpMessage();
            case DEADLINE:
                return Deadline.getHelpMessage();
            case EVENT:
                return Event.getHelpMessage();
            default:
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            throw new EchoException("Seems you have entered an invalid command.");
        }
    }
}
