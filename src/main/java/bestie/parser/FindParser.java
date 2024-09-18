package bestie.parser;

import bestie.command.Command;
import bestie.command.ErrorCommand;
import bestie.command.FindCommand;

import java.lang.reflect.Array;

public class FindParser {
    public Command executeFindCommand(String userInput) {
        try {
            String[] parts = userInput.split(" ");
            String commandWord = parts[0];
            return new FindCommand(parts[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new ErrorCommand("Please key in the keyword of the tasks you want to find.");
        }

    }
}
