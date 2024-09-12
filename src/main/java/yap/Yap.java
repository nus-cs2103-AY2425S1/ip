package yap;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import yap.storage.Storage;
import yap.task.TaskList;
import yap.ui.InputException;
import yap.ui.Ui;

/**
 * The main class that will run the Chatbot.
 */
public class Yap {
    public static void main(String[] args) {
        // Initialize Scanner
        Scanner scanner = new Scanner(System.in);

        // Print logo and introductions
        // String logo = "_    _  __     ______\n"
        //            + " \\  // //\\    ||__| |\n"
        //            + "  \\// //__\\   ||____/\n"
        //            + "  || //____\\  ||\n"
        //            + "  ||//      \\ ||\n";
        String logo = "Yap";
        String separator = "_____________________________________";
        System.out.println("Hello from " + logo);
        System.out.println("What would you like me to do for you?");
        System.out.println(separator);

        // Infinite loop to get user input
        Storage storage = new Storage("yap.txt", "./data/");
        TaskList taskList = new TaskList(storage);
        Ui ui = new Ui(taskList);

        while (true) {
            String userInput = scanner.nextLine();
            try {
                int userInputParseStatus = ui.reactToUserInput(userInput);
                if (userInputParseStatus == 0) {
                    break;
                }
            } catch (InputException | DateTimeParseException exception) {
                System.out.println(exception.getMessage());
                System.out.println(separator);
            }
        }
    }

    /**
     * Generates a response for the users' chat message
     */
    public String getResponse(String input) {
        return "Yap heard: " + input;
    }
}
