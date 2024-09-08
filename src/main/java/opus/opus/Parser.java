package opus;

/**
 * The Parser class is responsible for parsing user commands and extracting relevant
 * information such as the action, details, and specific components for tasks like deadlines
 * and events.
 */
public class Parser {

    /**
     * Parses the full command into an action and details.
     *
     * @param fullCommand The complete command entered by the user.
     * @return A string array containing the action and the rest of the command.
     */
    public static String[] parse(String fullCommand) {
        return fullCommand.split(" ", 2);
    }

    /**
     * Extracts the action (first word) from the command.
     *
     * @param fullCommand The complete command entered by the user.
     * @return The action part of the command.
     */
    public static String parseAction(String fullCommand) {
        return fullCommand.split(" ")[0];
    }

    /**
     * Extracts the details (everything after the first word) from the command.
     *
     * @param fullCommand The complete command entered by the user.
     * @return The details part of the command, or an empty string if none exists.
     */
    public static String parseDetails(String fullCommand) {
        String[] parts = fullCommand.split(" ", 2);
        return parts.length > 1 ? parts[1] : "";
    }

    /**
     * Parses the details for a deadline task, extracting the task description and deadline.
     *
     * @param details The details part of the command for a deadline task.
     * @return A string array containing the task description and the deadline.
     */
    public static String[] parseDeadlineDetails(String details) {
        return details.split(" /by ");
    }

    /**
     * Parses the details for an event task, extracting the task description, start time, and end time.
     *
     * @param details The details part of the command for an event task.
     * @return A string array containing the task description, start time, and end time.
     */
    public static String[] parseEventDetails(String details) {
        String[] parts = details.split(" /from ");
        String[] timeParts = parts[1].split(" /to ");
        return new String[]{parts[0], timeParts[0], timeParts[1]};
    }
}
