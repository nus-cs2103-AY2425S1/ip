package optimus;

import java.io.IOException;
import java.util.Scanner;

// Let ChatGPT check and suggest comments and JavaDocs according to CS2103T style guide
// Let ChatGPT check and suggest exceptions to catch
/**
 * Optimus class represents a simple task management system
 * that allows users to add, list, mark, and delete tasks.
 */
public class Optimus {
    //private static ArrayList<optimus.Task> taskList = new ArrayList<>(); // Stores the list of tasks
    private static final String FILE_PATH = "data/optimus.txt"; // File path for storing the task data
    private Storage storage; // Storage to handle loading and saving tasks
    private TaskList taskList; // TaskList to manage the list of tasks
    private Ui ui; // Ui to handle interactions with the user

    /**
     * Constructs an instance of the Optimus class.
     * Initializes the storage, task list, and UI components.
     * Loads tasks from the file, or initializes an empty task list if an error occurs.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Optimus(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.showError("Error loading tasks from file.");
            this.taskList = new TaskList();
        }
    }

    /**
     * Runs the main loop of the Optimus chatbot.
     * Handles user input and executes commands until the user exits.
     */
    public void run() {
        // Greeting for user
        ui.greetUser();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                String userInput = scanner.nextLine();
                String[] command = Parser.parseCommand(userInput);

                if (command[0].equals("bye")) { // Exit the program
                    ui.sayBye();
                    scanner.close();
                    break;
                } else if (command[0].equals("list")) { // List all tasks
                    ui.listTasks(taskList);
                } else if (command[0].equals("mark")) { // Mark a task as done
                    taskList.markTaskAsDone(Integer.parseInt(command[1]) - 1);
                    storage.saveTasks(taskList);
                } else if (command[0].equals("delete")) { // Delete a task
                    taskList.delete(Integer.parseInt(command[1]) - 1);
                    storage.saveTasks(taskList);
                } else if (command[0].equals("todo") || command[0].equals("deadline") || command[0].equals("event")) { // Add a task
                    taskList.addTask(userInput);
                    storage.saveTasks(taskList);
                } else { // Handle invalid command
                    ui.showError("Invalid command.");
                    ui.showExampleCommand();
                }
            } catch (OptimusException e) { // Handle custom exceptions
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Main method to start the Optimus chatbot.
     *
     * @param args Command line arguments (unused).
     */
    public static void main(String[] args) {
        new Optimus("data/optimus.txt").run();
    }
}