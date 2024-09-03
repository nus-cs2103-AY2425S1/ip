package edith;

import java.util.Objects;
import java.util.Scanner;

/**
 * This class is the entry point for chatbot Edith.
 */
public class Edith {

    /**
     * Handles all user inputs.
     * @param args User input.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ToDoList todoList = new ToDoList();
        Storage.loadTasks(todoList);

        // print out greeting when bot first starts up
        Ui.greetUser();

        String userInput = scanner.nextLine();

        // break out of loop when user inputs bye
        while (!Objects.equals(userInput, "bye")) {
            Ui.showLine();
            Ui.handleUserInput(userInput, todoList);
            Ui.showLine();
            userInput = scanner.nextLine();
        }

        Ui.bidFarewell();
    }
}
