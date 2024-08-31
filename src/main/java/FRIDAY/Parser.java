package FRIDAY;

import java.util.Arrays;

public class Parser {
    public static String parseCmd(String input) {
        String[] parts = input.split(" ");
        return parts[0];
    }

    public static String parseTaskDetails(String input) {
        String[] parts = input.split(" ");
        return String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));
    }

    public static Task parseDeadline(String taskDetails) {
        String[] deadlineDetails = taskDetails.split("/");
        String deadlineDescription = deadlineDetails[0].strip();
        String deadlineDeadline = deadlineDetails[1].strip();
        return new Deadline(deadlineDescription, deadlineDeadline, 0);
    }

    public static Task parseEvent(String taskDetails) {
        String[] eventDetails = taskDetails.split("/from");
        String[] startEnd = eventDetails[1].split("to");
        String eventDescription = eventDetails[0].trim();
        String eventStart = startEnd[0].trim();
        String eventEnd = startEnd[1].trim();
        return new Event(eventDescription, eventStart, eventEnd, 0);
    }
}
