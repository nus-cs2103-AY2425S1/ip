import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkCommandFactory extends CommandFactory {
    @Override
    public Command parse(String text) throws GrayException {
        Pattern pattern = Pattern.compile("(mark|unmark) (-?\\d+)");
        Matcher matcher = pattern.matcher(text);
        if (!matcher.matches()) return null;
        String command = matcher.group(1);
        int index = Integer.parseInt(matcher.group(2));
        return new MarkCommand(command, index);
    }
}
