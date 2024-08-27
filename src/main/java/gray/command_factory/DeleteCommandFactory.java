package gray.command_factory;

import gray.GrayException;
import gray.command.Command;
import gray.command.DeleteCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteCommandFactory extends CommandFactory {

    /**
     * Returns the delete command parsed from the text.
     *
     * @param text
     * @return Delete Task Command
     * @throws GrayException
     */
    @Override
    public Command parse(String text) throws GrayException {
        Pattern pattern = Pattern.compile("delete (-?\\d+)");
        Matcher matcher = pattern.matcher(text);
        if (!matcher.matches()) return null;
        int index = Integer.parseInt(matcher.group(1));
        return new DeleteCommand(index);
    }
}
