package bob;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import bob.task.Task;

/**
 * Represents the CLI user interface of Bob, depreciated for the GUI. Will be removed in future versions.
 */
public class Ui {

    /**
     * Prints a dialogue.
     *
     * @param input
     */
    public static void printDialogueCLI(String input) {
        System.out.println("___________________________________\n");
        System.out.println(input);
        System.out.println("___________________________________\n");
    }

    /**
     * Runs the dialogue with the user (only used for CLI mode).
     *
     * @param scanner
     * @param tasks
     */
    public static void runDialogue(Scanner scanner, List<Task> tasks) {
        Ui.printDialogueCLI("Hello! I'm Bob\nWhat can I do for you?");
        
        while (true) {
            try {
                String userInput = scanner.nextLine().trim();

                if (userInput.equalsIgnoreCase("bye")) {
                    Ui.printDialogueCLI("Bye. Hope to see you again soon!");
                    break;
                } else if (userInput.equalsIgnoreCase("list")) {
                    Parser.executeListCommand(tasks);
                } else if (userInput.startsWith("mark") || userInput.startsWith("unmark") || userInput.startsWith("delete")) {
                    Parser.executeTaskModificationCommands(userInput, tasks);
                } else if (userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")) {
                    Parser.executeTaskCreationCommands(userInput, tasks);
                } else {
                    throw new BobException("I'm sorry, but I don't know what that means :(");
                }
            } catch (BobException | IOException e) {
                Ui.printDialogueCLI(e.getMessage());
            }
        }
        scanner.close();
    }
}
