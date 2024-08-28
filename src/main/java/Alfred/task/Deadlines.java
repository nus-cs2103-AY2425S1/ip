package Alfred.task;

import Alfred.exception.AlfredException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadlines extends Task {
    private LocalDate deadline;

    public Deadlines(String description, String deadline) throws AlfredException {
        super(description);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new AlfredException("That is not a valid deadline Sir. It should go yyyy-mm-dd.");
        }
    }

    public Deadlines(String description, String deadline, boolean isDone) throws AlfredException {
        super(description);
        this.isDone = isDone;
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new AlfredException("That is not a valid deadline Sir. It should go yyyy-mm-dd.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public static Task createTask(String input) throws AlfredException {
        String regex = "^deadline\\s+(.+?)\\s+/by\\s+(\\d{4}-\\d{2}-\\d{2})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String description = matcher.group(1);
            String deadline = matcher.group(2);

            return new Deadlines(description, deadline);
        } else {
            throw new AlfredException("That is the wrong deadline format Sir. It goes deadline <task> " +
                    "/by yyyy-mm-dd");
        }
    }

    @Override
    public String toFileFormat() {
        return "D | " + getStatusIcon() + " | " + description + " | " + deadline;
    }
}
