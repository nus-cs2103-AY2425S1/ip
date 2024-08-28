import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{

    private final LocalDate byDate;
    public Deadline(String description) throws InputFormatException{
        super(getDescription(description));
        this.byDate = getDate(description);
    }

    public String toFileFormatString() {
        return String.format("D | %s | %s", super.toFileFormatString(), byDate.toString());
    }
    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)\n", super.toString(), byDate.toString());
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

    public static LocalDate getDate(String input) throws InputFormatException {
        String[] splitBy = input.split("/by",2);
        if (splitBy.length != 2) {
            throw new InputFormatException("Oops! I need a /by regex to save your deadline task");
        }
        try {
            return LocalDate.parse(splitBy[1].trim());
        } catch (DateTimeParseException e) {
            throw new InputFormatException("Please input your date in the format of YYYY-MM-DD");
        }
    }

    public static boolean matchDeadline(String s) {
        return s.startsWith("deadline");
    }
}
