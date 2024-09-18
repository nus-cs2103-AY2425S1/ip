package bestie.parser;

import bestie.command.Command;
import bestie.command.ErrorCommand;
import bestie.command.UnmarkCommand;

public class UnmarkParser {
    public Command executeUnmarkCommand(String userInput) {
        try {
            String[] parts = userInput.split(" ");
            String commandWord = parts[0];
            return new UnmarkCommand(Integer.parseInt(parts[1]) - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new ErrorCommand("Please indicate the index of the task you want to unmark.");
        }
    }
}
