package echo;


import java.time.format.DateTimeParseException;
import java.util.Objects;


/**
 * The Parser class is responsible for parsing user input commands and extracting necessary information
 * from those commands. It provides methods to handle different types of inputs related to tasks.
 */

public class Parser {
    /**
     * Parses the user's input command into its components.
     *
     * @param input The full input string provided by the user.
     * @return An array of strings where the first element is the command and the second element is the command details.
     */
    public static String[] parseCommand(String input) {
        return input.split(" ", 2);
    }

    /**
     * Parses the details of a deadline task from the user input.
     *
     * @param part The part of the input string that contains the deadline details.
     * @return A string of the task description
     */
    public static String parseDeadlineDes(String part) throws EchoException {
        String[] details = part.split(" /by ");
        if (details.length == 2) {
            return details[0];
        } else {
            throw new EchoException("Please specify the task description and deadline.");
        }
    }

    /**
     * Parses the details of a deadline task from the user input.
     *
     * @param part The part of the input string that contains the deadline details.
     * @return A string representation of the deadline.
     */
    public static String parseDeadlineTime(String part) throws EchoException {
        String[] details = part.split(" /by ");
        if (details.length == 2) {
            return details[1];
        } else {
            throw new EchoException("Please specify the task description and deadline.");
        }
    }

    /**
     * Parses the event times from the user input for an event task.
     *
     * @param part The part of the input string that contains the event details.
     * @return An array of strings where the first element is the start time and the second element is the end time.
     * @throws EchoException If the input string does not contain valid start and end times.
     */
    public static String[] parseEventTime(String part) throws EchoException {
        try {
            String[] details = part.split(" /from ", 2);
            if (details.length == 2) {
                String[] times = details[1].split(" /to ", 2);
                if (times.length == 2) {
                    return times;
                } else {
                    throw new EchoException("Please specify the task deadline.");
                }
            } else {
                throw new EchoException("Please specify the task description and deadline.");

            }
        } catch (DateTimeParseException e) {
            throw new EchoException("enter date in the yyyy-mm-dd format");
        }

    }

    /**
     * Extracts the event description from the user input for an event task.
     *
     * @param part The part of the input string that contains the event description and times.
     * @return The event description as a string.
     */
    public static String parseEventDes(String part) {
        return part.split(" /from ")[0];
    }

    /**
     * Checks if word toFInd is in task description
     *
     * @param des    String description of task
     * @param toFind String of word to be found
     * @return The event description as a string.
     */
    public static boolean isPresent(String des, String toFind) {
        String[] parts = des.split(" ");
        for (int i = 0; i < parts.length; i++) {
            if (Objects.equals(parts[i].toLowerCase(), toFind)) {
                return true;
            }
        }
        return false;
    }

    public static String parseEditNo(String part) {
        return part.split(" ")[0];
    }

    public static String parseEditDes(String part) {
        return part.split(" ")[1];
    }

}
