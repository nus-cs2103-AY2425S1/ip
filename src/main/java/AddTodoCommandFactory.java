import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddTodoCommandFactory extends CommandFactory {
    @Override
    public Command parse(String text) throws GrayException {
        Pattern pattern = Pattern.compile("todo (.*)");
        Matcher matcher = pattern.matcher(text);
        if (!matcher.matches()) return null;
        String description = matcher.group(1);
        if (description.isEmpty()) {
            throw new GrayException("OOPS!!! The description cannot be empty.");
        }
        return new AddTodoCommand(description);
    }
}
