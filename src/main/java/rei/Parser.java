package rei;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * This class encapsulates the parse method that takes the user prompt as its input
 */
public class Parser {
    private static final int MARK_COMMAND_LENGTH = 4;
    private static final int UNMARK_COMMAND_LENGTH = 6;
    private static final int TODO_COMMAND_LENGTH = 4;
    private static final int DEADLINE_COMMAND_LENGTH = 8;
    private static final int EVENT_COMMAND_LENGTH = 5;
    private static final int DELETE_COMMAND_LENGTH = 6;
    private static final int FIND_COMMAND_LENGTH = 4;

    /**
     * Different prompt types REI understands
     */
    public enum Prompt {LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, ANNYEONG};

    /**
     * Checks if a string only contains whitespace
     * @param input the string to be checked
     * @return true if the string only contains whitespace, false otherwise
     */
    public static boolean isAllWhitespace(String input) {
        return input.replaceAll("\\s+","").isEmpty();
    }

    /**
     * Parses a given user input
     * @param prompt the user input
     * @return a Prompt type
     */
    public static Prompt parse(String prompt) throws ReiException {

        List<String> prompts = Arrays.asList(prompt.split(" "));
        String taskDetails;
        switch (prompts.get(0)) {
            case "list":
                return Prompt.LIST;
            case "mark":
                // Read the rest of the line after "mark"
                taskDetails = prompt.substring(MARK_COMMAND_LENGTH).trim();

                // Check if the rest of the line is an integer
                if (taskDetails.isEmpty() || !taskDetails.matches("\\d+")) {
                    throw new ReiException("State the task number.");
                }

                return Prompt.MARK;
            case "unmark":
                // Read the rest of the line after "unmark"
                taskDetails = prompt.substring(UNMARK_COMMAND_LENGTH).trim();

                // Check if the rest of the line is an integer
                if (taskDetails.isEmpty() || !taskDetails.matches("\\d+")) {
                    throw new ReiException("State the task number.");
                }

                return Prompt.UNMARK;
            case "todo":
                if (isAllWhitespace(prompt.substring(TODO_COMMAND_LENGTH))) {
                    throw new ReiException("Task is empty. Please state the task name.");
                }

                return Prompt.TODO;
            case "deadline":
                if (isAllWhitespace(prompt.substring(DEADLINE_COMMAND_LENGTH))) {
                    throw new ReiException("Task is empty. Please state the task and deadline.");
                } else if (prompt.indexOf("/by") == -1) {
                    throw new ReiException("When is the deadline? Please state the task with the deadline.");
                } else if (isAllWhitespace(prompt.substring(8, prompt.indexOf("/by")))) {
                    throw new ReiException("Task name is empty. Please state the task and deadline.");
                }

                try {
                    LocalDateTime.parse(prompt.substring(prompt.indexOf("/by") + 4));
                } catch (DateTimeParseException e) {
                    throw new ReiException("Wrong date format : YYYY-MM-DDTHH:MM \n For example, 2024-09-12T18:00");
                }
                return Prompt.DEADLINE;
            case "event":
                if (isAllWhitespace(prompt.substring(EVENT_COMMAND_LENGTH))) {
                    throw new ReiException("Event is empty. Please state the event and time range.");
                } else if (prompt.indexOf("/from") == -1 || prompt.indexOf("/to") == -1) {
                    throw new ReiException("State the START and FINISH time of the event");
                } else if (isAllWhitespace(prompt.substring(5, prompt.indexOf("/from")))) {
                    throw new ReiException("Task name is empty. Please state the task and event time.");
                }

                try {
                    LocalDateTime.parse(prompt.substring(prompt.indexOf("/from") + 6, prompt.indexOf("/to") - 1));
                    LocalDateTime.parse(prompt.substring(prompt.indexOf("/to") + 4));
                } catch (DateTimeParseException e) {
                    throw new ReiException("Wrong date format : YYYY-MM-DDTHH:MM \n For example, 2024-09-12T18:00");
                }
                return Prompt.EVENT;
            case "delete":
                // Read the rest of the line after "delete"
                taskDetails = prompt.substring(DELETE_COMMAND_LENGTH).trim();

                // Check if the rest of the line is an integer
                if (taskDetails.isEmpty() || !taskDetails.matches("\\d+")) {
                    throw new ReiException("State the task number.");
                }

                return Prompt.DELETE;
            case "find":
                // Read the rest of the line after "find"
                prompt = prompt.substring(FIND_COMMAND_LENGTH).trim();

                if (prompt.isEmpty()) {
                    throw new ReiException("Please state the keyword!");
                }

                return Prompt.FIND;
            case "annyeong":
                return Prompt.ANNYEONG;
            default:
                throw new ReiException("I don't understand what you want me to do. \n" +
                        "Available commands : todo deadline event list delete mark unmark find annyeong");
        }
    }

}
