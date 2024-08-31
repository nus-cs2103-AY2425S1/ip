package ScoobyDoo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import exception.InputFormatException;
public class Parser {
    public static String getEventDescription(String input) throws InputFormatException{
        String[] splitDeadline = input.split(" ", 2);
        if (splitDeadline.length != 2) {
            throw new InputFormatException("Oops! I need a description to save your task");
        }

        String[] splitFromTo = splitDeadline[1].split("/from|/to",3);
        if (splitFromTo.length != 3) {
            throw new InputFormatException("Oops! I need a /from and a /to regex to save your event task");
        }
        return splitFromTo[0].trim();
    }

    public static LocalDateTime[] getEventFromAndToDate(String input) throws InputFormatException{
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

    public static String getDeadlineDescription(String input) throws InputFormatException{
        String[] splitDeadline = input.split(" ", 2);
        if (splitDeadline.length != 2) {
            throw new InputFormatException("Oops! I need a description to save your task");
        }
        String[] splitBySlash = splitDeadline[1].split("/by",2);
        if (splitBySlash.length != 2) {
            throw new InputFormatException("Oops! I need one /by regex to save your deadline task");
        }
        return splitBySlash[0].trim();
    }

    public static LocalDateTime getDeadlineDate(String input) throws InputFormatException {
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

    public static String getTodoDescription(String input) throws InputFormatException{
        String[] todoSplit = input.split(" ", 2);
        if (todoSplit.length != 2) {
            throw new InputFormatException("Oops!  I need a description to save your task");
        }
        return todoSplit[1].trim();
    }
}
