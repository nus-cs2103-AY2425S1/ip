package Cookie;
public class Parser {
    /**
     * Returns a command to be executed.
     *
     * @param input String to be parsed.
     * @return Command to be executed.
     */
    public String parseCommand(String input) {
        String[] splitInput = input.split(" ", 2);
        return splitInput[0];
    }

    /**
     * Returns the description of a task
     *
     * @param input String to be parsed.
     * @return Description of the task.
     */
    public String parseDescription(String input) {
        String[] splitInput = input.split(" ", 2);
        return (splitInput.length > 1) ? splitInput[1] : "";
    }

    /**
     * Returns the index of task.
     *
     * @param input String to be parsed.
     * @return Index of task.
     */
    public int parseInteger(String input) {
        return Integer.parseInt(input);
    }

    /**
     * Returns the deadline details.
     *
     * @param input String to be parsed.
     * @return Array containing deadline details of deadline task.
     */
    public String[] parseDeadline(String input) {
        return input.split(" /by ", 2);
    }

    /**
     * Returns the event start and end details.
     *
     * @param input String to be parsed.
     * @return Array containing event details of event task.
     */
    public String[] parseEvent(String input) {
        return input.split(" /from | /to ");
    }
}
