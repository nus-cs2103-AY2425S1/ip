package FRIDAY;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a parser class that is responsible for making sense of user input
 *
 * <p>
 *     This class is responsible for taking in Strings that the user inputs and converting them into
 *     information that the program can use to execute actions.
 * </p>
 */
public class Parser {
    private static final Set<String> allowedCommands = new HashSet<>(Arrays.asList(
            "todo", "event", "deadline", "mark", "list", "unmark", "delete", "archive", "search", "bye"));
    private static final Set<String> commandsWithoutDescription = new HashSet<>(Arrays.asList("list", "archive", "bye"));
    /**
     * This method takes in a string and returns the type of action that the program needs to execute
     * @param input a String representing user input
     * @return a String representing the type of action the proogram is meant to execute
     */
    public static String parseCmd(String input) throws FRIDAYException {
        String[] parts = input.split(" ");
        String keyword = parts[0].toLowerCase();
        if (!allowedCommands.contains(keyword)) {
            throw new FRIDAYException("Sorry, I don't know what this means :<");
        }
        return keyword;
    }

    /**
     * This method takes in a String representing user input and returns the
     * details of the task that is meant to be created
     * @param input String representing user input
     * @return a String consisting of all relevant task details
     */
    public static String parseTaskDetails(String input) throws FRIDAYException {
        String keyword = parseCmd(input);
        if (commandsWithoutDescription.contains(keyword)) {
            return "";
        }
        int firstSpaceIndex = input.indexOf(" ");
        if (firstSpaceIndex != -1) {
            String result = input.substring(firstSpaceIndex + 1);
            if (result.trim().isEmpty()) {
                throw new FRIDAYException("Oops! It appears that you have left the task description empty");
            }
            String[] parts = result.split(" ");
            String out = String.join(" ", parts);
            return out;
        } else {
            throw new FRIDAYException("Oops! It appears that you have left the task description empty");
        }
    }

    /**
     * this method returns a deadline task
     * @param taskDetails description of the deadline task, deadline date
     * @return deadline task
     */
    public static Task parseDeadline(String taskDetails) {
        String[] deadlineDetails = taskDetails.split("/");
        String deadlineDescription = deadlineDetails[0].strip();
        String deadlineDeadline = deadlineDetails[1].strip();
        return new Deadline(deadlineDescription, deadlineDeadline, 0);
    }

    /**
     * creates an Event task
     * @param taskDetails decsription, start and end times of the task
     * @return Event task
     */
    public static Task parseEvent(String taskDetails) {
        String[] eventDetails = taskDetails.split("/from");
        String[] startEnd = eventDetails[1].split("to");
        String eventDescription = eventDetails[0].trim();
        String eventStart = startEnd[0].trim();
        String eventEnd = startEnd[1].trim();
        return new Event(eventDescription, eventStart, eventEnd, 0);
    }
}
