package babblebot.parser;

/**
 * The Parser class interprets user input commands.
 * It extracts the information from the user's input
 * to determine the command and its associated details.
 */
public class Parser {

    /**
     * Parses the user input to extract the command keyword.
     *
     * @param userInput The full string of user input.
     * @return The command keyword as a String.
     */
    public static String parseCommand(String userInput) {
        return userInput.split(" ")[0];
    }

    /**
     * Parses the user input to extract the content of a todo task.
     *
     * @param userInput The full string of user input.
     * @return The content of the todo task as a String.
     */
    public static String parseTodoContent(String userInput) {
        return userInput.split("todo ")[1];
    }

    /**
     * Parses the user input to extract the content and deadline of a task.
     *
     * @param userInput The full string of user input.
     * @return A String array where the first element is the task content,
     *         and the second element is the deadline.
     */
    public static String[] parseDeadlineContent(String userInput) {
        String[] result = new String[2];
        result[0] = userInput.split("deadline ")[1].split(" /by")[0]; // content
        result[1] = userInput.split("/by ")[1]; // by
        return result;
    }

    /**
     * Parses the user input to extract the content, start time, and end time of an event.
     *
     * @param userInput The full string of user input.
     * @return A String array where the first element is the event content,
     *         the second element is the start time, and the third element is the end time.
     */
    public static String[] parseEventContent(String userInput) {
        String[] result = new String[3];
        result[0] = userInput.split("event ")[1].split(" /from")[0]; // eventContent
        String splitDates = userInput.split("/from ")[1];
        String[] parsedDates = splitDates.split(" /to ");
        result[1] = parsedDates[0]; // from
        result[2] = parsedDates[1]; // to
        return result;
    }

    /**
     * Parses the user input to extract the index of the task to be manipulated.
     *
     * @param userInput The full string of user input.
     * @return The index of the task (0-based index).
     */
    public static int parseIndex(String userInput) {
        return Integer.parseInt(userInput.split(" ")[1]) - 1;
    }
}
