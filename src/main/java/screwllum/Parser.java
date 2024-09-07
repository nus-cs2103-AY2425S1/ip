//package screwllum;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static List<String> parseUserInput(String input) {
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
                tokens.add(firstSegment[1].trim());
            } catch (Exception e) {
                System.out.println("The correct usage is: toggle <index:int>");
            }
            break;
        case "todo":
            try {
                tokens.add(firstSegment[1].trim());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("The correct usage is: todo <desc:string>");
            }
            break;
        case "deadline":
            try {
                tokens.add(firstSegment[1].trim());
                String[] secondSegment = inputSegments[1].split(" ", 2);
                if (!secondSegment[0].equals("by")) {
                    throw new Exception();
                }
                tokens.add(secondSegment[1].trim());
            } catch (Exception e) {
                System.out.println("The correct usage is: deadline <desc:string> /by <yyyy-MM-dd HH:mm>");
            }
            break;
        case "event":
            try {
                tokens.add(firstSegment[1].trim());
                String[] secondSegment = inputSegments[1].split(" ", 2);
                String[] thirdSegment = inputSegments[2].split(" ", 2);
                if (!secondSegment[0].equals("from") || !thirdSegment[0].equals("to")) {
                    throw new Exception();
                }
                tokens.add(secondSegment[1].trim());
                tokens.add(thirdSegment[1].trim());
                break;
            } catch (Exception e) {
                System.out.println("The correct usage is: event <desc:string> /from <yyyy-MM-dd HHmm>" 
                        + " /to <yyyy-MM-dd HHmm>");
            }
            break;
        default:
            tokens.set(0, "invalid");
        }
        return tokens;
    }
    
    public static LocalDateTime parseDateTime(String dateTimeString) {
        
        return parseDateTime(dateTimeString, DateTimeFormatter.ofPattern("yyyy-M-d HHmm"));
    }
    
    public static LocalDateTime parseDateTime(String dateTimeString, DateTimeFormatter pattern) {
        return LocalDateTime.parse(dateTimeString, pattern);
    }
}
