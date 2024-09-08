package screwllum;

import screwllum.exception.InvalidCommandException;
import screwllum.exception.InvalidDateFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static List<String> parseUserInput(String input) throws InvalidCommandException, InvalidDateFormatException {
        List<String> tokens = new ArrayList<>();
        String[] inputSegments = input.split("/");
        String[] firstSegment = inputSegments[0].split(" ", 2);
        tokens.add(firstSegment[0].toLowerCase());
        
        switch (tokens.get(0)) {
        case "bye":
            // Fallthrough
        case "list":
            break;
        case "delete":
            // Fallthrough
        case "toggle":
            try {
                int index = Integer.parseInt(firstSegment[1].trim());
                tokens.add(String.valueOf(index));
            } catch (Exception e) {
                throw new InvalidCommandException("The correct usage is: toggle <index:int>, " 
                        + "ensure that you inputted a number");
            }
            break;
        case "todo":
            try {
                tokens.add(firstSegment[1].trim());
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidCommandException("The correct usage is: todo <desc:string>, you must have missed out"
                        + " on adding the task description");
            }
            break;
        case "deadline":
            try {
                tokens.add(firstSegment[1].trim());
                String[] secondSegment = inputSegments[1].split(" ", 2);
                if (!secondSegment[0].equals("by")) {
                    throw new InvalidCommandException("Ensure that you typed /by followed by a space, "
                            + "when inputting your date");
                }
                tokens.add(secondSegment[1].trim());
                parseStringToDate(tokens.get(tokens.size() - 1));
            } catch (DateTimeParseException e) {
                throw new InvalidDateFormatException(tokens.get(tokens.size() - 1));
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidCommandException("The correct usage is: deadline <desc:string> /by <yyyy-M-d>");
            }
            break;
        case "event":
            try {
                tokens.add(firstSegment[1].trim());
                String[] secondSegment = inputSegments[1].split(" ", 2);
                String[] thirdSegment = inputSegments[2].split(" ", 2);
                if (!secondSegment[0].equals("from") || !thirdSegment[0].equals("to")) {
                    throw new InvalidCommandException("Ensure that you typed /from <startDate> /to <endDate>");
                }
                tokens.add(secondSegment[1].trim());
                tokens.add(thirdSegment[1].trim());
                parseStringToDate(tokens.get(tokens.size() - 1));
                parseStringToDate(tokens.get(tokens.size() - 2));
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidCommandException("The correct usage is: event <desc:string> /from <yyyy-M-d>" 
                        + " /to <yyyy-M-d>");
            } catch (DateTimeParseException e) {
                int size = tokens.size();
                throw new InvalidDateFormatException(tokens.get(size - 1) + " or " + tokens.get(size - 2));
            }
            break;
        default:
            throw new InvalidCommandException("Pardon me, I did not get what you mean");
        }
        return tokens;
    }
    
    public static LocalDate parseStringToDate(String dateString) {
        return parseStringToDate(dateString, "yyyy-M-d");
    }
    
    public static LocalDate parseStringToDate(String dateString, String pattern) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(pattern));
    }
    
    public static String parseDateToString(LocalDate localDate) {
        return parseDateToString(localDate, "yyyy-M-d");
    }
    
    public static String parseDateToString(LocalDate localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }
}
