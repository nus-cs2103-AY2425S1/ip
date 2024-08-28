import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{

    private final LocalDateTime fromDate;
    private final LocalDateTime toDate;

    public Event(String description) throws InputFormatException{
        super(getDescription(description));
        LocalDateTime[] fromTo = getFromAndToDate(description);
        this.fromDate = fromTo[0];
        this.toDate = fromTo[1];
    }

    public String toFileFormatString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("E | %s | %s | %s", super.toFileFormatString(), fromDate.format(formatter), toDate.format(formatter));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:ma");
        return String.format("[E] %s (from: %s to: %s)\n",super.toString(), fromDate.format(formatter), toDate.format(formatter));
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

    public static LocalDateTime[] getFromAndToDate(String input) throws InputFormatException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String[] splitFromTo = input.split("/from|/to",3);
        if (splitFromTo.length != 3) {
            throw new InputFormatException("Oops! I need a /from and a /to regex to save your event task");
        }
        try {
            LocalDateTime[] fromTo = {LocalDateTime.parse(splitFromTo[1].trim(), formatter),
                    LocalDateTime.parse(splitFromTo[2].trim(),formatter)};
            return fromTo;
        } catch (DateTimeParseException e) {
            throw new InputFormatException("Please input your date in the format of YYYY-MM-DD HH:mm");
        }

    }

    public static boolean matchEvent(String s) {
        return s.startsWith("event");
    }
}
