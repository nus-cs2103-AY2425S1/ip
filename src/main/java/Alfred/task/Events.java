package alfred.task;

import alfred.exception.AlfredException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Events extends Task {
    private LocalDate from;
    private LocalDate to;

    public Events(String description, String from, String to) throws AlfredException {
        super(description);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new AlfredException("There was an invalid deadline Sir. It should go yyyy-mm-dd.");
        }
    }

    public Events(String description, String from, String to, boolean isDone) throws AlfredException {
        super(description);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new AlfredException("There was an invalid deadline Sir. It should go yyyy-mm-dd.");
        }
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public static Task createTask(String input) throws AlfredException {
        String regex = "^event\\s+(.+?)\\s+/from\\s+" +
                "(\\d{4}-\\d{2}-\\d{2})\\s+/to\\s+(\\d{4}-\\d{2}-\\d{2})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String description = matcher.group(1);
            String from = matcher.group(2);
            String to = matcher.group(3);

            return new Events(description, from, to);
        } else {
            throw new AlfredException("That is the wrong events format Sir. It goes event <task> " +
                    "/from yyyy-mm-dd /to yyyy-mm-dd");
        }
    }

    @Override
    public String toFileFormat() {
        return "E | " + getStatusIcon() + " | " + description + " | " + from + " | " + to;
    }
}
