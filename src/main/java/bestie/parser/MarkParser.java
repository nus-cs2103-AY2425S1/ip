package bestie.parser;

import bestie.command.Command;
import bestie.command.ErrorCommand;
import bestie.command.MarkCommand;

public class MarkParser {

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
