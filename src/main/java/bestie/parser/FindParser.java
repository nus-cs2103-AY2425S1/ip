package bestie.parser;

import bestie.command.Command;
import bestie.command.ErrorCommand;
import bestie.command.FindCommand;

/**
 * Creates an instance of the find parser to parse a find command.
 */
public class FindParser {
    /**
     * Parses find command for execution.
     *
     * @param userInput User find command input.
     * @return Find command if parse successful, Error command if unsuccessful.
     */
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
