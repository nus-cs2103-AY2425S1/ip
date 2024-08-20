import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public static Task createTask(String input) {
        String regex = "^todo\\s+(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String description = matcher.group(1);
            return new ToDos(description);
        } else {
            throw new IllegalArgumentException("Invalid input: " + input);
        }
    }
}
