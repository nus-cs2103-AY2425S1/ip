package gray.command_factory;

import gray.GrayException;
import gray.command.Command;
import gray.command.FindCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindCommandFactory extends CommandFactory {
    @Override
    public Command parse(String text) throws GrayException {
        Pattern pattern = Pattern.compile("find (.*)");
        Matcher matcher = pattern.matcher(text);
        if (!matcher.matches()) return null;
        String search = matcher.group(1);
        if (search.isEmpty()) {
            throw new GrayException("The search query cannot be empty!");
        }
        return new FindCommand(search);
    }
}
