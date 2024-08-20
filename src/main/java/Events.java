import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Events extends Task {
    private String from;
    private String to;

    public Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    public static Task createTask(String input) throws AlfredException {
        String regex = "^event\\s+(.+?)\\s+/from\\s+(.+?)\\s+/to\\s+(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String description = matcher.group(1);
            String from = matcher.group(2);
            String to = matcher.group(3);

            return new Events(description, from, to);
        } else {
            throw new AlfredException("That is the wrong events format Sir. It goes event <task> " +
                    "/from <date> /to <date>");
        }
    }
}
