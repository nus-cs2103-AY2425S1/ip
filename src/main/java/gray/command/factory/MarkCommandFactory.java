package gray.command.factory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gray.GrayException;
import gray.command.Command;
import gray.command.MarkCommand;

/**
 * A parser that constructs a command that marks or un-marks a task as completed.
 */
public class MarkCommandFactory extends CommandFactory {

    /**
     * Returns the mark command parsed from the text.
     *
     * @param text
     * @return Mark Task Command
     * @throws GrayException
     */
    @Override
    public Command parse(String text) throws GrayException {
        Pattern pattern = Pattern.compile("(mark|unmark) (-?\\d+)");
        Matcher matcher = pattern.matcher(text);
        if (!matcher.matches()) {
            return null;
        }
        String command = matcher.group(1);
        int index = Integer.parseInt(matcher.group(2));
        return new MarkCommand(command, index);
    }
}
