package Alfred.task;

import Alfred.exception.AlfredException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    public ToDos(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public static Task createTask(String input) throws AlfredException {
        String regex = "^todo\\s+(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String description = matcher.group(1);
            return new ToDos(description);
        } else {
            throw new AlfredException("That is the wrong todo format Sir. It goes todo <task>");
        }
    }

    @Override
    public String toFileFormat() {
        return "T | " + getStatusIcon() + " | " + description;
    }
}
