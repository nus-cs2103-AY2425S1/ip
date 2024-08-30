package BrainRot;

/**
 * The Parser class is responsible for interpreting user input and breaking it down
 * into commands and details that can be processed by the application.
 * It categorizes the input into different command types such as "list", "bye", "mark", "unmark",
 * "delete", and "add", and returns these as a structured array.
 */
public class Parser {

    /**
     * Parses the user's input and categorizes it into a command and its details.
     * The method returns a string array where the first element is the command type
     * and the second element (if applicable) contains additional details such as the index
     * for "mark", "unmark", or "delete" commands, or the task details for "add" commands.
     *
     * @param userInput The raw input string from the user.
     * @return A string array where the first element is the command type and the second
     * element is the command's details or the task description.
     */
    public static String[] parse(String userInput) {
        String[] result = new String[2];

        if (userInput.equals("list")) {
            result[0] = "list";
        } else if (userInput.equals("bye")) {
            result[0] = "bye";
        } else if (userInput.startsWith("find")) {
            result[0] = "find";
            result[1] = userInput.substring(5).trim();
        } else if (userInput.startsWith("mark")) {
            result[0] = "mark";
            result[1] = userInput.substring(5).trim();  // Get the index
        } else if (userInput.startsWith("unmark")) {
            result[0] = "unmark";
            result[1] = userInput.substring(7).trim();  // Get the index
        } else if (userInput.startsWith("delete")) {
            result[0] = "delete";
            result[1] = userInput.substring(7).trim();  // Get the index
        } else {
            result[0] = "add";
            result[1] = userInput;  // Add task with the entire input as details
        }

        return result;
    }
}
