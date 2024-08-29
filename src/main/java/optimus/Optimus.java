package optimus;

import java.io.IOException;
import java.util.Scanner;

// Let ChatGPT check and suggest comments and JavaDocs according to CS2103T style guide
// Let ChatGPT check and suggest exceptions to catch
// Utilised ChatGPT to suggest better ways of doing OOP and which methods to pass
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
     * Initializes the UI, task list, and storage components.
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
            System.out.println("Error loading tasks from file: " + e.getMessage());
            this.taskList = new TaskList();
        }
    }

    /**
     * Runs the main loop of the Optimus chatbot.
     * Handles user input and executes commands until the user exits.
     */
    public void run() {
        ui.greetUser(); // Greeting for user
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
                    if (command.length != 2) { // Check if command is in format like "delete 2"
                        throw new OptimusException("Invalid format. Use 'delete [task number]'.");
                    }
                    taskList.delete(Integer.parseInt(command[1]) - 1);
                    storage.saveTasks(taskList);
                } else if (command[0].equals("todo") || command[0].equals("deadline") || command[0].equals("event")) { // Add a task
                    taskList.addTask(userInput);
                    storage.saveTasks(taskList);
                } else if (command[0].equals("find")) { // Find tasks which contain keyword and print them out
                    if (command.length != 2) { // Check if command is in format like "find book"
                        throw new OptimusException("Invalid format. Use 'find [keyword]'.");
                    }
                    ui.findTasks(userInput, taskList);
                } else { // Handle invalid command
                    ui.showExampleCommand();
                    throw new OptimusException("Invalid command.");
                }
            } catch (OptimusException e) { // Handle custom exceptions
                System.out.println(e.getMessage());
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