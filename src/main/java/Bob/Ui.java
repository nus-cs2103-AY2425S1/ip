package bob;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import bob.task.Task;

public class Ui {

    /**
     * Prints a dialogue.
     *
     * @param input
     */
    public static void dialogue(String input) {
        System.out.println("___________________________________\n");
        System.out.println(input);
        System.out.println("___________________________________\n");
    }

    /**
     * Runs the dialogue with the user.
     *
     * @param scanner
     * @param tasks
     */
    public static void runDialogue(Scanner scanner, List<Task> tasks) {
        Ui.dialogue("Hello! I'm Bob\nWhat can I do for you?");

        while (true) {
            try {
                String userInput = scanner.nextLine().trim();

                if (userInput.equalsIgnoreCase("bye")) {
                    Ui.dialogue("Bye. Hope to see you again soon!");
                    break;
                } else if (userInput.equalsIgnoreCase("list")) {
                    Parser.processListCommand(tasks);
                } else if (userInput.startsWith("mark") || userInput.startsWith("unmark") || userInput.startsWith("delete")) {
                    Parser.processTaskModificationCommands(userInput, tasks);
                } else if (userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")) {
                    Parser.processTaskCreationCommands(userInput, tasks);
                } else {
                    throw new BobException("I'm sorry, but I don't know what that means :(");
                }
            } catch (BobException | IOException e) {
                Ui.dialogue(e.getMessage());
            }
        }
        scanner.close();
    }
}
