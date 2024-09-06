import diomon.Commands;
import diomon.Storage;
import diomon.TaskList;

import java.util.Scanner;

/**
 * The {@code Main} class is the entry point for the Diomon application, which manages a task list.
 * It handles user input, interacts with the storage to save and load tasks, and executes commands.
 */
public class Main {

    /**
     * Prints a greeting message when the application starts.
     */
    private static void greeting() {
        String greetingMessage = "________________________________________________________________\nHello! I'm Diomon\nWhat do you need recorded?\n________________________________________________________________\n";
        System.out.print(greetingMessage);
    }

    /**
     * The main loop of the application.
     * It loads tasks from storage, listens for user input, executes commands, and saves tasks before exiting.
     */
    private void run() {
        // Initialise instance
        Storage storage = new Storage("data/data.txt");
        Commands commands = new Commands();
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList(storage.load());
        greeting();
        while (true) {
            String input = scanner.nextLine();
            System.out.println("________________________________________________________________");
            commands.run(input,taskList);
            System.out.println("________________________________________________________________");
            if (commands.isCanExit()) {
                storage.save(taskList.toStorageString());
                break;
            }
        }
    }

    /**
     * The main method to start the Diomon application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Main().run();
    }
}
