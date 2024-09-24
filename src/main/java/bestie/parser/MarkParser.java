package bestie.parser;

import bestie.command.Command;
import bestie.command.ErrorCommand;
import bestie.command.MarkCommand;

/**
 * Parser to parse the mark command.
 */
public class MarkParser {

    /**
     * Parses the mark command for execution.
     *
     * @param userInput User mark command input.
     * @return Mark command if parse successful, Error command if error occurs.
     */
    public Command executeMarkCommand(String userInput) {
        try {
            String[] parts = userInput.split(" ");
            String commandWord = parts[0];
            return new MarkCommand(Integer.parseInt(parts[1]) - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new ErrorCommand("Please indicate the index of the task you want to mark as complete");
        }
    }
}
