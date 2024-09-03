import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Todo extends Task {
    public Todo (String name) {
        super(name);
    }

    public static Todo handleInput(String input) throws WrongInputException{
        Pattern correctPattern = Pattern.compile("((\\w+\\s*)+)");
        Matcher correctMatcher = correctPattern.matcher(input);

        if (correctMatcher.matches()) {
            return new Todo(input);
        } else {
            throw new WrongInputException("This todo doesn't have a name!");
        }
    }

    public String creationResponse() {
        return "Ok! I've added a new todo task:\n" + this +
                "\n";
    }

    @Override
    public String toFileFormat() {
        return super.toFileFormat() + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
