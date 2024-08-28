import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{

    private final LocalDateTime byDate;
    public Deadline(String description) throws InputFormatException{
        super(getDescription(description));
        this.byDate = getDate(description);
    }

    public String toFileFormatString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("D | %s | %s", super.toFileFormatString(), byDate.format(formatter));
    }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:ma");
        return String.format("[D] %s (by: %s)\n", super.toString(), byDate.format(formatter));
    }

    public static String getDescription(String input) throws InputFormatException{
        String[] splitDeadline = input.split(" ", 2);
        if (splitDeadline.length != 2) {
            throw new InputFormatException("Oops! I need a description to save your task");
        }
        String[] splitBySlash = splitDeadline[1].split("/by",2);
        if (splitBySlash.length != 2) {
            throw new InputFormatException("Oops! I need one /by regex to save your deadline task");
        }
        return splitBySlash[0];
    }

    public static LocalDateTime getDate(String input) throws InputFormatException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String[] splitBy = input.split("/by",2);
        if (splitBy.length != 2) {
            throw new InputFormatException("Oops! I need a /by regex to save your deadline task");
        }
        try {
            return LocalDateTime.parse(splitBy[1].trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new InputFormatException("Please input your date in the format of YYYY-MM-DD HH:mm");
        }
    }

    public static boolean matchDeadline(String s) {
        return s.startsWith("deadline");
    }
}
