package evan.main;

import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "_".repeat(50);
    private final Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Evan!\nWhat can I do for you?");
        showLine();
    }

    public void showGoodbye() {
        System.out.println("Goodbye! Hope to see you again soon!");
        showLine();
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void showSuccess(String successMessage) {
        System.out.println(successMessage);
        showLine();
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
        showLine();
    }

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

    public String getUserInput() {
        String userInput = scanner.nextLine();
        showLine();
        return userInput;
    }
}
