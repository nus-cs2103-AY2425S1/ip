package seedu.avo;

import seedu.avo.commands.Command;
import seedu.avo.commands.CommandResult;
import seedu.avo.exceptions.AvoException;
import seedu.avo.parser.CommandParser;
import seedu.avo.ui.Response;

/**
 * Represents the bot
 */
public class Avo {
    private final CommandParser parser;
    public Avo(CommandParser parser) {
        this.parser = parser;
    }
    public Response getResponse(String input) {
        try {
            Command command = parser.parse(input);
            CommandResult result = command.execute(input);
            return new Response(result.getMessage());
        } catch (AvoException e) {
            return new Response(e.getMessage());
        }
    }
}
