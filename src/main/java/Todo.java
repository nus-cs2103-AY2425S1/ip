import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Todo extends Task {
    private Todo (String name) {
        super(name);
    }

    public static Todo handleInput(String input) throws WrongInputException{
        Pattern correctPattern = Pattern.compile("((\\w+\\s*)+)");
        Matcher correctMatcher = correctPattern.matcher(input);

        Pattern wrongPattern = Pattern.compile("\\s*");
        Matcher wrongMatcher = wrongPattern.matcher(input);

        if (correctMatcher.matches()) {
            return new Todo(input);
        } else {
            throw new WrongInputException("This todo doesn't have a name!");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
