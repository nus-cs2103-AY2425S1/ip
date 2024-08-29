import data.NoSuchCommandException;
import storage.FileCorruptedException;
import storage.Storage;
import storage.StorageOperationException;
import task.TaskList;
import task.TaskType;

import java.util.Scanner;

/**
 * Entry point of Mylo.
 * Initializes the application and starts the interaction with the user.
 */
public class Main {
    private final String name = "Mylo";

    private TaskList taskList;

    private static void separator() {
        System.out.println("--------------------------------");
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        try {
            init();
            showWelcomeMessage();
            runCommandLoopUntilExitCommand();
            exit();
        } catch (FileCorruptedException | StorageOperationException e) {
            System.out.println(e.getMessage() + "\nPlease try again.");
        }
    }

    /**
     * Initializes the required objects and loads up the data from the storage file.
     */
    public void init() throws FileCorruptedException, StorageOperationException {
        Storage storage = new Storage();
        taskList = storage.load();
    }

    /**
     * Prints the Goodbye message and terminates the program.
     */
    public void exit() {
        String goodbye = "Goodbye. Have a nice day ahead!";

        separator();
        System.out.println(goodbye);
        separator();
    }

    /**
     * Prints welcome message.
     */
    public void showWelcomeMessage() {
        String greet = "Hello! Thanks for using " + name + ".";
        String opening_query = "What can I help you?";

        System.out.println(greet);
        System.out.println(opening_query);
    }

    /**
     * Reads the user command and executes it, until the user issues the exit command.
     */
    public void runCommandLoopUntilExitCommand() {
        Scanner scanner = new Scanner(System.in);

        String input = "bye";

        if (scanner.hasNext()) input = scanner.nextLine();

        while (!input.equals("bye")) {
            String[] keys = input.split(" ");
            try {
                switch (keys[0]) {
                case "list":
                    separator();
                    taskList.showTasks(input.substring(4));
                    separator();
                    break;
                case "delete":
                    separator();
                    taskList.deleteTask(Integer.parseInt(keys[1]));
                    separator();
                    break;
                case "mark":
                    separator();
                    taskList.markTaskAsDone(Integer.parseInt(keys[1]));
                    separator();
                    break;
                case "unmark":
                    separator();
                    taskList.markTaskAsUndone(Integer.parseInt(keys[1]));
                    separator();
                    break;
                case "todo":
                    separator();
                    taskList.addTask(input.substring(4), TaskType.TODO);
                    separator();
                    break;
                case "deadline":
                    separator();
                    taskList.addTask(input.substring(8), TaskType.DEADLINE);
                    separator();
                    break;
                case "event":
                    separator();
                    taskList.addTask(input.substring(5), TaskType.EVENT);
                    separator();
                    break;
                case "who are you":
                    separator();
                    System.out.println("I'm " + name);
                    separator();
                    break;
                default:
                    throw new NoSuchCommandException(input);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                input = scanner.nextLine();
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
