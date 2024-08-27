import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteCommandFactory extends CommandFactory {
    @Override
    public Command parse(String text) throws GrayException {
        Pattern pattern = Pattern.compile("delete (-?\\d+)");
        Matcher matcher = pattern.matcher(text);
        if (!matcher.matches()) return null;
        int index = Integer.parseInt(matcher.group(1));
        return new DeleteCommand(index);
    }
}
