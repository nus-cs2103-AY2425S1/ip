package devon;

import java.util.Arrays;

/**
 * Parses user input to extract commands and relevant details for task management.
 * Handles extraction of different task-related information from user input strings.
 */
public class Parser {
    public Parser() { }

    /**
     * Extracts the command from a user input message.
     *
     * @param msg The input message containing the command.
     * @return The command extracted from the input message.
     */
    protected String extractCommand(String msg) {
        String[] parts = msg.split(" ");
        return parts[0];
    }

    /**
     * Extracts the content from a user input message after the command.
     *
     * @param input The input message containing the command and content.
     * @return The content of the message after the command, or "Error" if no content is found.
     */
    protected String extractContent(String input) {
        String[] parts = input.split(" ");
        return parts.length > 1
                ? String.join(" ", Arrays.copyOfRange(parts, 1, parts.length))
                : "Error";
    }

    /**
     * Extracts the task index from the user input.
     *
     * @param input The input message containing the task index.
     * @return The task index extracted from the input.
     * @throws NumberFormatException If the task index is not a valid integer.
     */
    protected int extractTaskIndex(String input) throws NumberFormatException {
        return Integer.parseInt(extractContent(input));
    }

    /**
     * Extracts the description for a Todo task from the user input.
     *
     * @param input The input message containing the Todo task description.
     * @return The description of the Todo task.
     */
    protected String extractTodo(String input) {
        return extractContent(input).trim();
    }

    /**
     * Extracts the description and deadline from the user input for a Deadline task.
     *
     * @param input The input message containing the Deadline task details.
     * @return An array containing the description and deadline for the Deadline task.
     * @throws DevonInvalidDeadlineException If the input does not contain the "/by" keyword.
     */
    protected String[] extractDeadline(String input) throws DevonInvalidDeadlineException {
        String content = extractContent(input);
        if (!content.contains("/by")) {
            throw new DevonInvalidDeadlineException();
        }
        String[] parts = content.split("/by", 2);
        String description = parts[0].trim();
        String by = parts[1].trim();
        return new String[]{ description, by };
    }

    /**
     * Extracts the description, start date and time, and end date and time from the user input for an Event task.
     *
     * @param input The input message containing the Event task details.
     * @return An array containing the description, start date and time, and end date and time for the Event task.
     * @throws DevonInvalidEventException If the input does not contain both "/from" and "/to" keywords.
     */
    protected String[] extractEvent(String input) throws DevonInvalidEventException {
        String content = extractContent(input);
        if (!(content.contains("/from") && content.contains("/to"))) {
            throw new DevonInvalidEventException();
        }
        String[] partsFrom = content.split("/from", 2);
        String[] partsTo = partsFrom[1].split("/to", 2);
        String description = partsFrom[0].trim();
        String from = partsTo[0].trim();
        String to = partsTo[1].trim();
        return new String[]{ description, from, to };
    }
}
