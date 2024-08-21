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
     * Print out "Bee" logo in ASCII art
     */
    public static final String logo = " ____\n"
            + "|  _ \\  ___   ___\n"
            + "| |_/  / _ \\ / _ \\\n"
            + "| |_\\ |  __/|  __/\n"
            + "|____/ \\___| \\___|\n";

    public static final String help = """
            There are three types of task, you can add them by typing:
                todo <task name>
                deadline <task name> /by <time>
                event <task name> /from <time> /by <time>
               
            You can view all your tasks and their respective indices by:
                list
               
            You edit your tasks by:
                mark <task index>
                unmark <task index>
                delete <task index>
            """;

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

    /**
     * Format chatbot response after deleting a task
     * Inform user of total number of tasks in todolist
     *
     * @param deleted string representation of task deleted
     * @param numOfTasks total number of tasks added
     */
    public static void deleteTaskResponse(String deleted, int numOfTasks) {
        printBtnLines(String.format(
                "Noted. I've removed this task:\n   %s\nNow you have %d tasks in the list.",
                deleted, numOfTasks));
    }
}
