package FRIDAY;

import java.util.Arrays;

/**
 * Represents a parser class that is responsible for making sense of user input
 *
 * <p>
 *     This class is responsible for taking in Strings that the user inputs and converting them into
 *     information that the program can use to execute actions.
 * </p>
 */
public class Parser {
    /**
     * This method takes in a string and returns the type of action that the program needs to execute
     * @param input a String representing user input
     * @return a String representing the type of action the proogram is meant to execute
     */
    public static String parseCmd(String input) {
        String[] parts = input.split(" ");
        return parts[0];
    }

    /**
     * This method takes in a String representing user input and returns the details of the task that is meant to be created
     * @param input String representing user input
     * @return a String consisting of all relevant task details
     */
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
