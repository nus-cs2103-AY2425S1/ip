package commands;

import exceptions.DukeException;

public class HelpCommand {
    public static String getHelp(String userInput) throws DukeException {
        if (userInput.equals("help")) {
            return getGeneralHelp();
        }

        if (!isValidCommand(userInput)) {
            throw new DukeException("Please enter a valid command if you need help");
        }

        return getCommandHelp(userInput);
    }

    public static boolean isValidCommand(String userInput) {
        String[] commandArray = userInput.split(" ");

        if (commandArray.length != 2) {
            return false;
        }
        return true;
    }

    public static String getGeneralHelp() {
        String message = "These are common EchoBot commands used:\n";
        for (Command command : Command.values()) {
            message += "\t" + command.name().toLowerCase() + "\n";
        }
        return message + "See 'help [command]' to read about a specific command.";
    }

    public static String getCommandHelp(String userInput) throws DukeException {
        String command = userInput.split(" ")[1].toUpperCase();

        try {
            switch (Command.valueOf(command)) {
            case BYE:
                return "bye";
            case HI:
                return "hi";
            case LIST:
                return "list";
            case MARK:
                return "mark";
            case UNMARK:
                return "unmark";
            case TAG:
                return "tag";
            case DELETE:
                return "delete";
            case FIND:
                return "find";
            case HELP:
                return "help";
            default:
                return "task";
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("Seems you have entered an invalid command.\n" + " "
                    + "You can enter 'help' or 'help [command]' to get more information.");
        }
    }
}
