import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {
    private final String startDate;
    private final String endDate;

    private Event(String name, String startDate, String endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Event handleInput(String input) throws WrongInputException {
        Pattern correctPattern =
                Pattern.compile("((\\w+\\s*)+) /from ((\\w+\\s*)+) /to ((\\w+\\s*)+)");
        Matcher correctMatcher = correctPattern.matcher(input);

        Pattern wrongPattern1 = Pattern.compile(
                "(\\s*|\\s*/from|\\s*/to|\\s*/from.*/to ((.+\\s*)*))");
        Matcher wrongMatcher1 = wrongPattern1.matcher(input);

        Pattern wrongPattern2 = Pattern.compile("((.+\\s*)*)( /from)?( /to)?");
        Matcher wrongMatcher2 = wrongPattern2.matcher(input);

        if (correctMatcher.matches()) {
            return new Event(correctMatcher.group(1),
                    correctMatcher.group(3), correctMatcher.group(5));
        } else if (wrongMatcher1.matches()) {
            throw new WrongInputException("Hmmm... This event doesn't have a name!");
        } else if (wrongMatcher2.matches()) {
            throw new WrongInputException("You forgot to specify a start and end date!");
        } else {
            //Shouldn't reach if all error cases handled
            throw new WrongInputException("Something's wrong!");
        }
    }

    @Override
    public String toString() {
        String dateString = " (from: " + startDate + " to: " +
                endDate + ")";
        return "[E]" + super.toString() + dateString;
    }
}
