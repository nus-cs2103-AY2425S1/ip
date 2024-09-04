package Nave;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task{
    private final LocalDate endDate;

    public Deadline(String name, LocalDate endDate) {
        super(name);
        this.endDate = endDate;
    }

    public static Deadline handleInput(String input) throws WrongInputException {
        Pattern correctPattern = Pattern.compile("((\\w+\\s*)+) /by (\\d{4}-\\d{2}-\\d{2})");
        Matcher correctMatcher = correctPattern.matcher(input);

        Pattern wrongPattern1 = Pattern.compile("(\\s*|\\s*/by((.+\\s*)*))");
        Matcher wrongMatcher1 = wrongPattern1.matcher(input);

        Pattern wrongPattern2 = Pattern.compile("((.+\\s*)*) (/by)?");
        Matcher wrongMatcher2 = wrongPattern2.matcher(input);

        Pattern wrongPattern3 = Pattern.compile("((\\w+\\s*)+) /by (\\d+-\\d+-\\d+|.+)");
        Matcher wrongMatcher3 = wrongPattern3.matcher(input);

        if (correctMatcher.matches()) {
            LocalDate date = LocalDate.parse(correctMatcher.group(3));
            return new Deadline(correctMatcher.group(1), date);
        } else if (wrongMatcher3.matches()) {
            throw new WrongInputException("Your date format is wrong!");
        } else if (wrongMatcher1.matches()) {
            throw new WrongInputException("Hmmm... This deadline doesn't have a name!");
        } else if (wrongMatcher2.matches()) {
            throw new WrongInputException("You forgot to specify a due date!");
        }  else {
            //Shouldn't reach if all error cases handled
            throw new WrongInputException("Something's wrong!");
        }
    }

    public String creationResponse() {
        return "Ok! I've added a new task with a deadline:\n" + this +
                "\n";
    }

    @Override
    public String toFileFormat() {
        return super.toFileFormat() + "," + endDate.toString() + System.lineSeparator();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLL yyyy EEE");
        String endDateString = " (by: " + endDate.format(formatter) + ")";
        return "[D]" + super.toString() + endDateString;
    }
}
