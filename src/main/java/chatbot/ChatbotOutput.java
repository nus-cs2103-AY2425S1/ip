package chatbot;

import todo.Task;

/**
 * Formats string to standardise chatbot
 * output format
 *
 * @author celeschai
 */
public class ChatbotOutput {
    /**
     * Ensure correct indentation for any text
     * Indent single and multiline strings
     *
     * @param multilineString single or multiline string
     * @return formatted string for chatbot output
     */
    public static String multiLineIndent(String multilineString) {
        String[] lines = multilineString.split("\n");

        // Create a StringBuilder for efficient concatenation
        StringBuilder modifiedString = new StringBuilder();

        // Append 4 characters in front of each line
        String indent = "    ";
        for (String line : lines) {
            modifiedString.append(indent).append(line).append("\n");
        }

        return modifiedString.toString();
    }

    /**
     * Format standard output to be sandwiched by two lines
     *
     * @param content text to be included between 2 lines
     */
    public static void printBtnLines(String content) {
        String line = "______________________________________" +
                "_______________________________________\n";

        System.out.println(line + multiLineIndent(content) + line);
    }

    /**
     * Format chatbot response after adding a task
     * Inform user of total number of tasks in todolist
     *
     * @param added string representation of task added
     * @param numOfTasks total number of tasks added
     */
    public static void addTaskResponse(String added, int numOfTasks) {
        printBtnLines(String.format(
                "Got it. I've added this task:\n   %s\nNow you have %d tasks in the list.",
                added, numOfTasks));
    }
}
