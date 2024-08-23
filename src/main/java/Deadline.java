import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task{
    private final String endDate;

    private Deadline(String name, String endDate) {
        super(name);
        this.endDate = endDate;
    }

    public static Deadline handleInput(String input) throws WrongInputException {
        Pattern correctPattern = Pattern.compile("((\\w+\\s*)+) /by ((\\w+\\s*)+)");
        Matcher correctMatcher = correctPattern.matcher(input);

        Pattern wrongPattern1 = Pattern.compile("(\\s*|\\s*/by ((.+\\s*)*))");
        Matcher wrongMatcher1 = wrongPattern1.matcher(input);

        Pattern wrongPattern2 = Pattern.compile("((.+\\s*)*)( /by)?");
        Matcher wrongMatcher2 = wrongPattern2.matcher(input);

        if (correctMatcher.matches()) {
            return new Deadline(correctMatcher.group(1), correctMatcher.group(3));
        } else if (wrongMatcher1.matches()) {
            throw new WrongInputException("Hmmm... This deadline doesn't have a name!");
        } else if (wrongMatcher2.matches()) {
            throw new WrongInputException("You forgot to specify a due date!");
        } else {
            //Shouldn't reach if all error cases handled
            throw new WrongInputException("Something's wrong!");
        }
    }

    @Override
    public String toString() {
        String endDateString = " (by: " + endDate + ")";
        return "[D]" + super.toString() + endDateString;
    }
}
