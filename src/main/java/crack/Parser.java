package crack;

/**
 * The Parser class handles the interpretation and parsing of user input.
 * It extracts commands and relevant data such as task descriptions, dates,
 * and task numbers from the input provided by the user.
 */
public class Parser {

    /**
     * Parses the user input and returns the command as a string.
     * The command is assumed to be the first word in the input.
     *
     * @param input the raw input string provided by the user.
     * @return the command part of the input.
     */
    public static String parseCommand(String input) {
        return input.split(" ")[0]; // Return the first word as the command
    }

    /**
     * Parses a "mark" or "unmark" command to extract the task number.
     * The task number is expected to be the second word in the input.
     * This method converts the 1-based task number into a 0-based index.
     *
     * @param input the input string containing the "mark" or "unmark" command.
     * @return the 0-based index of the task to mark or unmark.
     * @throws IllegalArgumentException if the task number format is invalid.
     */
    public static int parseTaskNumber(String input) throws IllegalArgumentException {
        try {
            return Integer.parseInt(input.split(" ")[1]) - 1; // Task numbers are 1-based, convert to 0-based index
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid task number format.");
        }
    }

    /**
     * Parses a "todo" command to extract the task description.
     * The description is expected to follow the "todo" keyword.
     *
     * @param input the input string containing the "todo" command.
     * @return the description of the todo task.
     * @throws IllegalArgumentException if the todo description is empty.
     */
    public static String parseTodoDescription(String input) throws IllegalArgumentException {
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new IllegalArgumentException("The description of a todo cannot be empty.");
        }
        return description;
    }

    /**
     * Parses a "deadline" command to extract the task description and deadline
     * date.
     * The description should precede the "/by" delimiter and the deadline should
     * follow it.
     *
     * @param input the input string containing the "deadline" command.
     * @return a string array where the first element is the task description and
     *         the second is the deadline.
     * @throws IllegalArgumentException if the format of the deadline is invalid or
     *                                  incomplete.
     */
    public static String[] parseDeadline(String input) throws IllegalArgumentException {
        try {
            String[] parts = input.substring(9).split(" /by ");
            if (parts.length < 2 || parts[0].trim().isEmpty()) {
                throw new IllegalArgumentException(
                    "Invalid format for deadline. Use: deadline <description> /by <yyyy-mm-dd>.");
            }
            return new String[] {
                parts[0].trim(), parts[1].trim()
            }; // Return description and deadline date
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(
                "Invalid format for deadline. Use: deadline <description> /by <yyyy-mm-dd>.");
        }
    }

    /**
     * Parses an "event" command to extract the task description, start date, and
     * end date.
     * The description should precede the "/from" delimiter, the start date should
     * follow "/from",
     * and the end date should follow "/to".
     *
     * @param input the input string containing the "event" command.
     * @return a string array where the first element is the task description, the
     *         second is the start date, and the third is the end date.
     * @throws IllegalArgumentException if the format of the event is invalid or
     *                                  incomplete.
     */
    public static String[] parseEvent(String input) throws IllegalArgumentException {
        try {
            String[] parts = input.substring(6).split(" /from | /to ");
            if (parts.length < 3 || parts[0].trim().isEmpty()) {
                throw new IllegalArgumentException(
                    "Invalid format for event. Use: event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>.");
            }
            return new String[] {
                parts[0].trim(), parts[1].trim(), parts[2].trim()
            }; // Return description, start, and
            // end dates
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(
                "Invalid format for event. Use: event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>.");
        }
    }
}
