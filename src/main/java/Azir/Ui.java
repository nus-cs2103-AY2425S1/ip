package Azir;

/**
 * Ui deals with interactions with the user.
 */
public class Ui {
    /**
     * Returns the chatbot welcome message.
     *
     * @return Welcome message.
     */
    public static String showWelcome() {
        String response = "";
        response += "Hello! I'm the emperor of shurima!\n";
        response += "Here are the available commands to communicate with the emperor\n" +
                "1. list\n2. mark [task number]\n3. unmark[task number]\n" +
                "4. delete[task number]\n5. todo [description]\n6. deadline [description] /by [yyyy-mm-dd]\n" +
                "7. event [description] /from [date] /to [date]\n8. find [keyword]\n";
        response += "What can I do for you my soldier?";
        return response;
    }

    /**
     * Returns the chatbot exit message
     */
    public String showExit() {
        return "Goodbye fellow soldier!";
    }

    /**
     * Returns message for list and find command.
     *
     * @param command User input.
     * @return Ending chatbot message.
     */
    public String showCommandEndMessage(String command) {
        switch (command) {
        case "list":
            return "Here are the tasks in your list:\n";

        case "find":
            return "Here are the matching tasks in your list:\n";

        default:
            assert false: command;
            return "";
        }
    }

    /**
     * Returns message for all commands except list.
     *
     * @param command User input.
     * @param task Corresponding task for command.
     * @return Ending chatbot message.
     */
    public String showCommandEndMessage(String command, String task) {
        String botOutput = "";
        switch (command) {
        case "mark":
            botOutput += "Well done solider! I've marked this task as done:\n";
            botOutput += task + "\n";
            return botOutput;

        case "unmark":
            botOutput += "I've marked this task as not done yet, keep up soldier\n";
            botOutput += task + "\n";
            return botOutput;

        case "delete":
            botOutput += "I have removed this task:\n";
            botOutput += task + "\n";
            return botOutput;

        case "todo":
            botOutput += "More work for my soldiers! I've added this task:\n";
            botOutput += task + "\n";
            return botOutput;

        case "deadline":
            botOutput += "More work for my soldiers! I've added this task:\n";
            botOutput += task + "\n";
            return botOutput;

        case "event":
            botOutput += "More work for my soldiers! I've added this task:\n";
            botOutput += task + "\n";
            return botOutput;

        default:
            assert false: command;
            return "";
        }
    }

    /**
     * Returns number of tasks in tasklist.
     *
     * @param size Size of task list.
     * @return Number of tasks as a string.
     */
    public String showTaskNumber(int size) {
        return String.format("Now you have %d %s in the list\n", size, size == 1 ? "task" : "tasks");
    }

    /**
     * Returns the error message.
     *
     * @param message The error message.
     * @return Error message.
     */
    public String showError(String message) {
        return message;
    }
}
