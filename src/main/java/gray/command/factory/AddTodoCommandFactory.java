package gray.command.factory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gray.GrayException;
import gray.command.AddTodoCommand;
import gray.command.Command;

/**
 * A parser that adds a command that adds a to-do task.
 */
public class AddTodoCommandFactory extends CommandFactory {

    /**
     * Returns the add to-do command parsed from the text.
     *
     * @param text
     * @return Add to-do task command
     * @throws GrayException
     */
    @Override
    public Command parse(String text) throws GrayException {
        Pattern pattern = Pattern.compile("todo (.*)");
        Matcher matcher = pattern.matcher(text);
        if (!matcher.matches()) {
            return null;
        }
        String description = matcher.group(1);
        if (description.isEmpty()) {
            throw new GrayException("OOPS!!! The description cannot be empty.");
        }
        return new AddTodoCommand(description);
    }
}
