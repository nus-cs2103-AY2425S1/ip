package yapbot.util;

import yapbot.commands.*;
import yapbot.exceptions.YapBotException;

public class Parser {

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

            default:
                throw new YapBotException("Error, supporting module for user command: \"" + command + "\" not "
                        + "found.\nYapBot may not support this feature.");

        }
    }
}
