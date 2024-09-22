package FRIDAY;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a parser that is responsible for making sense of user input.
 *
 * <p>
 *     This class is responsible for taking in Strings that the user inputs and converting them into
 *     information that the program can use to execute actions.
 * </p>
 */
public class Parser {
    private static final Set<String> allowedCommands = new HashSet<>(Arrays.asList(
            "todo", "event", "deadline", "mark", "list", "unmark", "delete", "archive", "search", "bye"));
    private static final Set<String> commandsWithoutDescription = new HashSet<>(Arrays.asList("list",
            "archive", "bye"));

    /**
     * This method takes in a string and returns the type of action that the program needs to execute.
     *
     * @param input String representing user input.
     * @return String representing the type of action the program is meant to execute.
     * @throws FRIDAYException If the the command is not recognized.
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
     * details of the task that is meant to be created.
     *
     * @param input String representing user input.
     * @return String consisting of all relevant task details.
     * @throws FRIDAYException If the relevant details are not entered.
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
     * This method creates a Deadline task containing all relevant details as input by the user.
     *
     * @param taskDetails String containing the description of the deadline task and the deadline date.
     * @return Deadline task.
     */
    public static Task parseDeadline(String taskDetails) {
        String[] deadlineDetails = taskDetails.split("/");
        String deadlineDescription = deadlineDetails[0].strip();
        String deadlineDeadline = deadlineDetails[1].strip();
        return new Deadline(deadlineDescription, deadlineDeadline, 0);
    }

    /**
     * This method creates an Event task containing all relevant details as input by the user.
     *
     * @param taskDetails String containing the description, start and end times of the task.
     * @return Event task.
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
