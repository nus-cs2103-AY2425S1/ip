package edith;

import java.util.Objects;
import java.util.Scanner;

/**
 * This class is the entry point for chatbot Edith.
 */
public class Edith {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ToDoList TO_DO_LIST = new ToDoList();

    /**
     * Handles all user inputs.
     * @param args User input.
     * @deprecated Due to use of GUI instead, getResponse method utilised instead.
     */
    public static void main(String[] args) {
        Storage.loadTasks(TO_DO_LIST);

        // print out greeting when bot first starts up
        Ui.greetUser();

        String userInput = SCANNER.nextLine();

        // break out of loop when user inputs bye
        while (!Objects.equals(userInput, "bye")) {
            Ui.showLine();
            Ui.handleUserInput(userInput, TO_DO_LIST);
            Ui.showLine();
            userInput = SCANNER.nextLine();
        }

        Ui.bidFarewell();
    }

    public String getResponse(String input) {
        return Ui.handleUserInput(input, TO_DO_LIST);
    }

    public void loadTasks() {
        Storage.loadTasks(TO_DO_LIST);
    }
}
