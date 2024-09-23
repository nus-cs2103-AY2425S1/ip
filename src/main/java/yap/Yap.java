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
    private Ui ui;

    /**
     * The constructor for creating an instance of Yap.
     */
    public Yap() {
        Storage storage = new Storage("yap.txt", "./data/");
        TaskList taskList = new TaskList(storage);
        this.ui = new Ui(taskList);
    }

    /**
     * Gets a response from yap depending on the users' input from the GUI.
     *
     * @param input The users' input string
     * @return A string of the response from yap
     */
    public String getResponse(String input) {
        // Get the response from the Ui instance
        String response = "";
        try {
            response = ui.reactToUserInput(input);
        } catch (InputException exception) {
            response = exception.getMessage();
        }
        return response;
    }

    /**
     * The method that starts yap up
     */
    public void run() {
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

        while (true) {
            String userInput = scanner.nextLine();
            try {
                if (userInput.equalsIgnoreCase("bye")) {
                    System.out.println("Bye! It was really nice talking to you, see you soon :)");
                    break;
                }
                String yapReaction = ui.reactToUserInput(userInput);
            } catch (InputException | DateTimeParseException exception) {
                System.out.println(exception.getMessage());
                System.out.println(separator);
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        // Initialize Scanner
        Yap yap = new Yap();
        yap.run();
    }
}
