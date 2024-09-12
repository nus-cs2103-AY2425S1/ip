package rei;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class Parser {

    private static final int MARK_COMMAND_LENGTH = 4;
    private static final int UNMARK_COMMAND_LENGTH = 6;
    private static final int TODO_COMMAND_LENGTH = 4;
    private static final int DEADLINE_COMMAND_LENGTH = 8;
    private static final int EVENT_COMMAND_LENGTH = 5;
    private static final int DELETE_COMMAND_LENGTH = 6;

    public enum Prompt {LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, ANNYEONG, UNKNOWN};
    public static boolean isAllWhitespace(String input) {
        return input.replaceAll("\\s+","").isEmpty();
    }
    public static Prompt parse(TaskList tasks, String prompt) {

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
                    Ui.print("State the task number.");
                    return Prompt.UNKNOWN;
                }

                return Prompt.MARK;
            case "unmark":
                // Read the rest of the line after "unmark"
                taskDetails = prompt.substring(UNMARK_COMMAND_LENGTH).trim();

                // Check if the rest of the line is an integer
                if (taskDetails.isEmpty() || !taskDetails.matches("\\d+")) {
                    Ui.print("State the task number.");
                    return Prompt.UNKNOWN;
                }

                return Prompt.UNMARK;
            case "todo":
                if (isAllWhitespace(prompt.substring(TODO_COMMAND_LENGTH))) {
                    Ui.print("Task is empty. Please state the task name.");
                    return Prompt.UNKNOWN;
                }

                return Prompt.TODO;
            case "deadline":
                if (isAllWhitespace(prompt.substring(DEADLINE_COMMAND_LENGTH))) {
                    Ui.print("Task is empty. Please state the task and deadline.");
                    return Prompt.UNKNOWN;
                } else if (prompt.indexOf("/by") == -1) {
                    Ui.print("When is the deadline? Please state the task with the deadline.");
                    return Prompt.UNKNOWN;
                } else if (isAllWhitespace(prompt.substring(8, prompt.indexOf("/by")))) {
                    Ui.print("Task name is empty. Please state the task and deadline.");
                    return Prompt.UNKNOWN;
                }

                try {
                    LocalDateTime.parse(prompt.substring(prompt.indexOf("/by") + 4));
                } catch (DateTimeParseException e) {
                    Ui.print("Wrong date format : YYYY-MM-DDTHH:MM \n For example, 2024-09-12T18:00");
                    return Prompt.UNKNOWN;
                }
                return Prompt.DEADLINE;
            case "event":
                if (isAllWhitespace(prompt.substring(EVENT_COMMAND_LENGTH))) {
                    Ui.print("Event is empty. Please state the event and time range.");
                    return Prompt.UNKNOWN;
                } else if (prompt.indexOf("/from") == -1 || prompt.indexOf("/to") == -1) {
                    Ui.print("State the START and FINISH time of the event");
                    return Prompt.UNKNOWN;
                } else if (isAllWhitespace(prompt.substring(5, prompt.indexOf("/from")))) {
                    Ui.print("Task name is empty. Please state the task and event time.");
                    return Prompt.UNKNOWN;
                }

                try {
                    LocalDateTime.parse(prompt.substring(prompt.indexOf("/from") + 6, prompt.indexOf("/to") - 1));
                    LocalDateTime.parse(prompt.substring(prompt.indexOf("/to") + 4));
                } catch (DateTimeParseException e) {
                    Ui.print("Wrong date format : YYYY-MM-DDTHH:MM \n For example, 2024-09-12T18:00");
                    return Prompt.UNKNOWN;
                }
                return Prompt.EVENT;
            case "delete":
                // Read the rest of the line after "delete"
                taskDetails = prompt.substring(DELETE_COMMAND_LENGTH).trim();

                // Check if the rest of the line is an integer
                if (taskDetails.isEmpty() || !taskDetails.matches("\\d+")) {
                    Ui.print("State the task number.");
                    return Prompt.UNKNOWN;
                }

                return Prompt.DELETE;
            case "annyeong":
                Ui.print("Annyeong. Hope to see you soon.");
                return Prompt.ANNYEONG;
            default:
                Ui.print("I don't understand what you want me to do.");
                return Prompt.UNKNOWN;
        }
    }

}
