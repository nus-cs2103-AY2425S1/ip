package crack;
public class Parser {

    // Parses the user input and returns the command in the form of a string
    public static String parseCommand(String input) {
        return input.split(" ")[0];  // Return the first word as the command
    }

    // Parses a "mark" or "unmark" command to extract the task number
    public static int parseTaskNumber(String input) throws IllegalArgumentException {
        try {
            return Integer.parseInt(input.split(" ")[1]) - 1;  // Task numbers are 1-based, convert to 0-based index
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid task number format.");
        }
    }

    // Parses a "todo" command to extract the task description
    public static String parseTodoDescription(String input) throws IllegalArgumentException {
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new IllegalArgumentException("The description of a todo cannot be empty.");
        }
        return description;
    }

    // Parses a "deadline" command to extract the task description and deadline date
    public static String[] parseDeadline(String input) throws IllegalArgumentException {
        try {
            String[] parts = input.substring(9).split(" /by ");
            if (parts.length < 2 || parts[0].trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid format for deadline. Use: deadline <description> /by <yyyy-mm-dd>.");
            }
            return new String[]{parts[0].trim(), parts[1].trim()};  // Return description and deadline date
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid format for deadline. Use: deadline <description> /by <yyyy-mm-dd>.");
        }
    }

    // Parses an "event" command to extract the task description, start date, and end date
    public static String[] parseEvent(String input) throws IllegalArgumentException {
        try {
            String[] parts = input.substring(6).split(" /from | /to ");
            if (parts.length < 3 || parts[0].trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid format for event. Use: event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>.");
            }
            return new String[]{parts[0].trim(), parts[1].trim(), parts[2].trim()};  // Return description, start, and end dates
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid format for event. Use: event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>.");
        }
    }
}
