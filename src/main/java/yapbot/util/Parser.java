package yapbot.util;

import yapbot.commands.*;

import yapbot.exceptions.YapBotException;

/**
 * Processes user input and passes input accordingly to relevant classes.
 */
public class Parser {

    /**
     * Parses user input.
     *
     * @param input user input.
     * @return A Command instance based on user's entered command.
     * @throws YapBotException If no command was entered or if the command is invalid.
     */
    public static Command parse(String input) throws YapBotException {

        // Remove leading and trailing whitespace from user input
        input = input.strip();
        int spaceCharPos = input.indexOf(" ");
        String command;
        String commandDetails;

        // Parse user input and separates out command from other details
        if (spaceCharPos == -1) {
            command = input;
            commandDetails = "";
        } else {
            command = input.substring(0, input.indexOf(" "));
            commandDetails = input.substring(input.indexOf(" ") + 1).strip();
        }

        if (command.isEmpty()) {
            throw new YapBotException("Error, User Input Prediction module offline.\nManual input required.");
        }

        switch (command) {
            case "bye":
                return new ByeCommand();

            case "list":
                return new ListCommand();

            case "mark":
                return new MarkCommand(commandDetails);

            case "unmark":
                return new UnmarkCommand(commandDetails);

            case "todo":
                return new ToDoCommand(commandDetails);

            case "deadline":
                return new DeadlineCommand(commandDetails);

            case "event":
                return new EventCommand(commandDetails);

            case "delete":
               return new DeleteCommand(commandDetails);

            case "find":
                return new FindCommand(commandDetails);

            default:
                throw new YapBotException("Error, supporting module for user command: \"" + command + "\" not "
                        + "found.\nYapBot may not support this feature.");

        }
    }
}
