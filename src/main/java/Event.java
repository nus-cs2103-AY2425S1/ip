import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Event extends Task{

    private final LocalDate fromDate;
    private final LocalDate toDate;

    public Event(String description) throws InputFormatException{
        super(getDescription(description));
        LocalDate[] fromTo = getFromAndToDate(description);
        this.fromDate = fromTo[0];
        this.toDate = fromTo[1];
    }

    public String toFileFormatString() {
        return String.format("D | %s | %s | %s", super.toFileFormatString(), fromDate.toString(), toDate.toString());
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)\n",super.toString(), fromDate.toString(), toDate.toString());
    }

    public static String getDescription(String input) throws InputFormatException{
        String[] splitDeadline = input.split(" ", 2);
        if (splitDeadline.length != 2) {
            throw new InputFormatException("Oops! I need a description to save your task");
        }

        String[] splitFromTo = splitDeadline[1].split("/from|/to",3);
        if (splitFromTo.length != 3) {
            throw new InputFormatException("Oops! I need a /from and a /to regex to save your event task");
        }
        return splitFromTo[0];
    }

    public static LocalDate[] getFromAndToDate(String input) throws InputFormatException{
        String[] splitFromTo = input.split("/from|/to",3);
        if (splitFromTo.length != 3) {
            throw new InputFormatException("Oops! I need a /from and a /to regex to save your event task");
        }
        try {
            LocalDate[] fromTo = {LocalDate.parse(splitFromTo[1].trim()), LocalDate.parse(splitFromTo[2].trim())};
            return fromTo;
        } catch (DateTimeParseException e) {
            throw new InputFormatException("Please input your date in the format of YYYY-MM-DD");
        }

    }

    public static boolean matchEvent(String s) {
        return s.startsWith("event");
    }
}
