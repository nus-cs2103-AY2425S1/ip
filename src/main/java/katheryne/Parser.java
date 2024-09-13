package katheryne;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static String[] parseCommand(String input) {
        return input.split(" ",2);
    }

    public static String getCommandWord(String input) {
        return parseCommand(input)[0];
    }

    public static String getToDoDes(String str) {
        return parseCommand(str)[1];
    }

    public static String getDeadlineTime(String input) {
        return input.split("/by",2)[1].trim();
    }

    public static String getDeadlineDes(String input) {
        return parseCommand(input)[1].split("/by ",2)[0];
    }

    public static String[] getEventTimeAndDescription(String input) {
        String fromTime = "";
        String toTime = "";
        String description = "";
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");

        if (fromIndex == -1) {
            fromTime = "-1";
        } else if (toIndex == -1) {
            toTime = "-1";
        } else {
            int firstSpaceIndex = input.indexOf(" ");
            description = input.substring(firstSpaceIndex + 1, fromIndex).trim();
            fromTime = input.substring(fromIndex + "/from".length(), toIndex).trim();
            toTime = input.substring(toIndex + "/to".length()).trim();
            String fullCommand = parseCommand(input)[1];
        }

        String[] res = {fromTime, toTime, description};
        return res;
    }

    public static String getEventFromTime(String input) {
        return getEventTimeAndDescription(input)[0];
    }

    public static String getEventToTime(String input) {
        return getEventTimeAndDescription(input)[1];
    }

    public static String getEventDes(String input) {
        return getEventTimeAndDescription(input)[2];
    }

    public static LocalDate dateInputFormatter(String str) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(str, inputFormatter);
    }

    public static String dateOutputFormatter(LocalDate date) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(outputFormatter);
    }

    public static boolean isValidDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static String getFindKeyWord(String str) {
        return Parser.parseCommand(str)[1];
    }






}
