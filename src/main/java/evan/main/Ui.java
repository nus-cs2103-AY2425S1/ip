package evan.main;

import java.util.Scanner;

/**
 * Represents the command line user interface that the user interacts with.
 */
public class Ui {
    private static final String DIVIDER = "_".repeat(50);
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Prints a welcome message.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Evan!\nWhat can I do for you?");
        showLine();
    }

    /**
     * Prints a goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Goodbye! Hope to see you again soon!");
        showLine();
    }

    /**
     * Prints a horizontal line that acts as a divider.
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints a success message.
     *
     * @param successMessage Success message to be printed.
     */
    public void showSuccess(String successMessage) {
        System.out.println(successMessage);
        showLine();
    }

    /**
     * Prints an error message.
     *
     * @param errorMessage Error message to be printed.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
        showLine();
    }

    /**
     * Prints the valid commands that the chatbot understands.
     * Used to inform the user of what commands they can use to interact with the chatbot.
     */
    public void showValidCommands() {
        String commandList = """
                Commands supported by Evan:
                - list
                - todo <description>
                - deadline <description> /by <when>
                - event <description> /from <start> /to <end>
                - mark <task_number>
                - unmark <task_number>
                - delete <task_number>
                - find <description>
                - exit""";
        System.out.println(commandList);
        showLine();
    }

    /**
     * Prompts the user to input a command.
     *
     * @return User's input.
     */
    public String getUserInput() {
        String userInput = scanner.nextLine();
        showLine();
        return userInput;
    }
}
