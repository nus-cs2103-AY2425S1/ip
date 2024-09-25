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
    private static final int TAG_COMMAND_LENGTH = 3;
    private static final int UNTAG_COMMAND_LENGTH = 5;
    private static final int VIEWTAGS_COMMAND_LENGTH = 8;


    // Solution below inspired by https://github.com/1st2GetThisName/ip/blob/master/src/main/java/vecrosen/Parser.java
    /**
     * Different prompt types REI understands
     */
    public enum Prompt {LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, TAG, UNTAG, VIEWTAGS, ANNYEONG};

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
        String[] details;
        switch (prompts.get(0)) {
            case "list":
                return Prompt.LIST;
            case "mark":
                return getMarkPrompt(prompt);
            case "unmark":
                return getUnmarkPrompt(prompt);
            case "todo":
                return getToDoPrompt(prompt);
            case "deadline":
                return getDeadlinePrompt(prompt);
            case "event":
                return getEventPrompt(prompt);
            case "delete":
                return getDeletePrompt(prompt);
            case "find":
                return getFindPrompt(prompt);
            case "tag":
                return getTagPrompt(prompt);
            case "untag":
                return getUntagPrompt(prompt);
            case "viewtags":
                return getViewtagsPrompt(prompt);
            case "annyeong":
                return Prompt.ANNYEONG;
            default:
                throw new ReiException("I don't understand what you want me to do. \n" +
                        "Available commands : todo deadline event list delete mark unmark find tag untag viewtags annyeong");
        }
    }

    /**
     * Parses the user input and check if the command is in the correct format.
     * Returns the "viewtags" prompt.
     * @param prompt The user input string, in the correct format should contain the "viewtags" command followed by a task number.
     * @return Prompt.VIEWTAGS if the task number is successfully parsed.
     * @throws ReiException If the task number is missing or is not a valid integer.
     */
    private static Prompt getViewtagsPrompt(String prompt) throws ReiException {
        String taskDetails;
        // Read the rest of the line after "view"
        taskDetails = prompt.substring(VIEWTAGS_COMMAND_LENGTH).trim();

        // Check if the rest of the line is an integer
        if (taskDetails.isEmpty() || !taskDetails.matches("\\d+")) {
            throw new ReiException("State the task number!");
        }

        return Prompt.VIEWTAGS;
    }

    /**
     * Parses the user input and check if the command is in the correct format.
     * Returns the "untag" prompt.
     * @param prompt The user input string, in the correct format should contain the "untag" command followed by a task number and a tag.
     * @return Prompt.UNTAG if both the task number and tag are successfully parsed and valid.
     * @throws ReiException If the input is missing, the task number is not an integer, there is more than one tag,
     * the tag does not start with '#', or the tag is otherwise invalid.
     */
    private static Prompt getUntagPrompt(String prompt) throws ReiException {
        String[] details;
        String taskDetails;
        // Read the rest of the line after "untag"
        taskDetails = prompt.substring(UNTAG_COMMAND_LENGTH).trim();
        details = taskDetails.split(" ");

        if (taskDetails.isEmpty() || details.length != 2 || !details[0].matches("\\d+")) {
            throw new ReiException("State the task number and ONE tag!");
        }

        if (!details[1].startsWith("#")) {
            throw new ReiException("The tag must start with a '#'!");
        } else if (details[1].equals("#")) {
            throw new ReiException("The tag cannot be empty!");
        } else if (details[1].substring(1).contains("#")) {
            throw new ReiException("Invalid tag!");
        }

        return Prompt.UNTAG;
    }

    /**
     * Parses the user input and check if the command is in the correct format.
     * Returns the "tag" prompt.
     * @param prompt The user input string, in the correct format should contain the "tag" command followed by a task number and one or more tags.
     * @return Prompt.TAG if both the task number and tags are successfully parsed and valid.
     * @throws ReiException If the input is missing, the task number is not an integer, there are no tags,
     * a tag does not start with '#', or a tag is otherwise invalid.
     */
    private static Prompt getTagPrompt(String prompt) throws ReiException {
        String taskDetails;
        String[] details;
        // Read the rest of the line after "tag"
        taskDetails = prompt.substring(TAG_COMMAND_LENGTH).trim();
        details = taskDetails.split(" ");

        if (taskDetails.isEmpty() || details.length < 2 || !details[0].matches("\\d+")) {
            throw new ReiException("State the task number and the tag(s)!");
        }

        for (int i = 1; i < details.length; i++) {
            if (!details[i].startsWith("#")) {
                throw new ReiException("All tags must start with a '#'!");
            } else if (details[i].equals("#")) {
                throw new ReiException("Tags cannot be empty!");
            } else if (details[i].substring(1).contains("#")) {
                throw new ReiException("Invalid tag!");
            }
        }

        return Prompt.TAG;
    }

    /**
     * Parses the user input and check if the command is in the correct format.
     * Returns the "find" prompt.
     * @param prompt The user input string, in the correct format should contain the "find" command followed by a keyword.
     * @return Prompt.FIND if the keyword is successfully parsed.
     * @throws ReiException If the input is empty or if the keyword is missing.
     */
    private static Prompt getFindPrompt(String prompt) throws ReiException {
        // Read the rest of the line after "find"
        prompt = prompt.substring(FIND_COMMAND_LENGTH).trim();

        if (prompt.isEmpty()) {
            throw new ReiException("Please state the keyword!");
        }

        return Prompt.FIND;
    }

    /**
     * Parses the user input and check if the command is in the correct format.
     * Returns the "delete" prompt.
     * @param prompt The user input string, in the correct format should contain the "delete" command followed by a task number.
     * @return Prompt.DELETE if the task number is successfully parsed and valid.
     * @throws ReiException If the input is missing or if the task number is not a valid integer.
     */
    private static Prompt getDeletePrompt(String prompt) throws ReiException {
        String taskDetails;
        // Read the rest of the line after "delete"
        taskDetails = prompt.substring(DELETE_COMMAND_LENGTH).trim();

        // Check if the rest of the line is an integer
        if (taskDetails.isEmpty() || !taskDetails.matches("\\d+")) {
            throw new ReiException("State the task number.");
        }

        return Prompt.DELETE;
    }

    /**
     * Parses the user input and check if the command is in the correct format.
     * Returns the "event" prompt.
     * @param prompt The user input string, in the correct format should include the "event" command followed by the event name and the time details.
     * @return Prompt.EVENT if the event details are successfully parsed and valid.
     * @throws ReiException  If the input is empty, the start or finish time is missing, the task name is empty,
     * or if the date-time formats are incorrect.
     */
    private static Prompt getEventPrompt(String prompt) throws ReiException {
        if (isAllWhitespace(prompt.substring(EVENT_COMMAND_LENGTH))) {
            throw new ReiException("Event is empty. Please state the event and time range!");
        } else if (prompt.indexOf("/from") == -1 || prompt.indexOf("/to") == -1) {
            throw new ReiException("State the START and FINISH time of the event!");
        } else if (isAllWhitespace(prompt.substring(5, prompt.indexOf("/from")))) {
            throw new ReiException("Task name is empty. Please state the task and event time!");
        }

        try {
            LocalDateTime.parse(prompt.substring(prompt.indexOf("/from") + 6, prompt.indexOf("/to") - 1));
            LocalDateTime.parse(prompt.substring(prompt.indexOf("/to") + 4));
        } catch (DateTimeParseException e) {
            throw new ReiException("Wrong date format : YYYY-MM-DDTHH:MM \n For example, 2024-09-12T18:00");
        }
        return Prompt.EVENT;
    }

    /**
     * Parses the user input and check if the command is in the correct format.
     * Returns the "deadline" prompt.
     * @param prompt The user input string, in the correct format should include the "deadline" command followed by the task name and the deadline.
     * @return Prompt.DEADLINE if the task name and deadline are successfully parsed and valid.
     * @throws ReiException If the input is empty, the deadline is missing, the task name is empty,
     * or if the date-time format for the deadline is incorrect.
     */
    private static Prompt getDeadlinePrompt(String prompt) throws ReiException {
        if (isAllWhitespace(prompt.substring(DEADLINE_COMMAND_LENGTH))) {
            throw new ReiException("Task is empty. Please state the task and deadline!");
        } else if (prompt.indexOf("/by") == -1) {
            throw new ReiException("When is the deadline? Please state the task with the deadline!");
        } else if (isAllWhitespace(prompt.substring(8, prompt.indexOf("/by")))) {
            throw new ReiException("Task name is empty. Please state the task and deadline!");
        }

        try {
            LocalDateTime.parse(prompt.substring(prompt.indexOf("/by") + 4));
        } catch (DateTimeParseException e) {
            throw new ReiException("Wrong date format : YYYY-MM-DDTHH:MM \n For example, 2024-09-12T18:00");
        }
        return Prompt.DEADLINE;
    }

    /**
     * Parses the user input and check if the command is in the correct format.
     * Returns the "todo" prompt.
     * @param prompt The user input string, in the correct format should include the "todo" command followed by the task name.
     * @return Prompt.TODO if the task name is successfully parsed and valid.
     * @throws ReiException If the task name is empty or not provided.
     */
    private static Prompt getToDoPrompt(String prompt) throws ReiException {
        if (isAllWhitespace(prompt.substring(TODO_COMMAND_LENGTH))) {
            throw new ReiException("Task is empty. Please state the task name!");
        }

        return Prompt.TODO;
    }

    /**
     * Parses the user input and check if the command is in the correct format.
     * Returns the "unmark" prompt.
     * @param prompt The user input string, in the correct format should include the "unmark" command followed by the task number.
     * @return Prompt.UNMARK if the task number is successfully parsed and valid.
     * @throws ReiException If the input is missing or if the task number is not a valid integer.
     */
    private static Prompt getUnmarkPrompt(String prompt) throws ReiException {
        String taskDetails;
        // Read the rest of the line after "unmark"
        taskDetails = prompt.substring(UNMARK_COMMAND_LENGTH).trim();

        // Check if the rest of the line is an integer
        if (taskDetails.isEmpty() || !taskDetails.matches("\\d+")) {
            throw new ReiException("State the task number!");
        }

        return Prompt.UNMARK;
    }

    /**
     * Parses the user input and check if the command is in the correct format.
     * Returns the "mark" prompt.
     * @param prompt The user input string, in the correct format should include the "mark" command followed by the task number.
     * @return Prompt.MARK if the task number is successfully parsed and valid.
     * @throws ReiException If the input is missing or if the task number is not a valid integer.
     */
    private static Prompt getMarkPrompt(String prompt) throws ReiException {
        String taskDetails;
      
        // Read the rest of the line after "mark"
        taskDetails = prompt.substring(MARK_COMMAND_LENGTH).trim();

        // Check if the rest of the line is an integer
        if (taskDetails.isEmpty() || !taskDetails.matches("\\d+")) {
            throw new ReiException("State the task number!");
        }

        return Prompt.MARK;
    }

}
