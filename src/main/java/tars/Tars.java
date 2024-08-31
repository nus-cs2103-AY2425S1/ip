package tars;

import tars.storage.Storage;
import tars.tasks.TaskList;
import java.util.Scanner;

/**
 * The main class for running the TARS application.
 *
 * <p>The {@code Tars} class handles the user interaction loop, allowing users to
 * interact with the task management system by entering commands. It provides functionality
 * to display introductory messages, process user input, execute commands, and save tasks
 * to storage.</p>
 */
public class Tars {

    /**
     * Starts the TARS application and manages the user interaction loop.
     *
     * <p>This method initializes the necessary components, such as {@link Storage}, {@link Response},
     * and {@link TaskList}, and continuously listens for user input. Depending on the user's commands,
     * it will perform different actions, such as listing tasks, adding new tasks, and exiting the application.</p>
     */
    public void run() {

        Storage storage = new Storage();
        Response response = new Response();
        TaskList tasks = new TaskList(storage.updateTasks());

        response.intro();

        Scanner scanner = new Scanner(System.in);

        boolean isExit = false;

        while (!isExit) {
            System.out.print(">>>");
            String userInput = scanner.nextLine().trim();

            switch (userInput) {
            case "bye":
                response.outro();
                scanner.close();
                isExit = true;

                break;

            case "list":
                response.showList(tasks);
                break;

            default:
                response.generateResponse(userInput, tasks);
                break;
            }
        }

        storage.saveTasks(tasks);



    }

    /**
     * The main entry point for the TARS application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {

        Tars tars = new Tars();
        tars.run();
    }
}
