import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadlines extends Task {
    private String deadline;

    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    public static Task createTask(String input) {
        String regex = "^deadline\\s+(.+?)\\s+/by\\s+(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String description = matcher.group(1);
            String deadline = matcher.group(2);

            return new Deadlines(description, deadline);
        } else {
            throw new IllegalArgumentException("Invalid deadline: " + input);
        }
    }
}
