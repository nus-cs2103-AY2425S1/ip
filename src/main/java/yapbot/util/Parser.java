package yapbot.util;

import yapbot.commands.ByeCommand;
import yapbot.commands.Command;
import yapbot.commands.DeadlineCommand;
import yapbot.commands.DeleteCommand;
import yapbot.commands.EventCommand;
import yapbot.commands.FindCommand;
import yapbot.commands.ListCommand;
import yapbot.commands.MarkCommand;
import yapbot.commands.ToDoCommand;
import yapbot.commands.UnmarkCommand;
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
        if (input.isEmpty()) {
            throw new YapBotException("Error, User Input Prediction module offline.\nManual input required.");
        }

        String strippedInput = input.strip();
        int spaceCharPos = strippedInput.indexOf(" ");

        String command;
        String commandDetails;

        if (spaceCharPos == -1) {
            command = strippedInput;
            commandDetails = "";
        } else {
            command = strippedInput.substring(0, input.indexOf(" "));
            commandDetails = strippedInput.substring(input.indexOf(" ") + 1).strip();
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
