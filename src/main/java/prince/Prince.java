package prince;

import exception.IncompleteDescException;
import exception.InvalidDeadlineException;
import exception.UnknownWordException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import task.Task;
import task.EventTask;
import task.DeadlinesTask;
import task.ToDoTask;
import ui.Ui;

import java.io.File;
import java.util.Scanner;

/**
 * Main class for Prince bot
 *
 * This class initialises and runs Prince, managing the TODO list.
 * it interacts with the parser to parse input commands, interacts with the UI to give
 * proper output messages, interacts with the storage functions to store data of the user
 * and maintains an arraylist of tasks for easy editing and retrieval.
 *
 */

public class Prince {

    //prince.txt is a file that will automatically be created if it doesnt exist
    //stores the tasks

    private Storage storage;
    private Ui ui;
    private Parser parser;

    private TaskList taskList;

    public enum TaskType {
        todo,
        deadline,
        event
    }

    public static final String TASKS_FILE_PATH = "data/prince.Prince.txt";

    /**
     * Constructor of Prince
     *
     * Initialises the various components and creates the files necessary for storage
     * Citations : Used ChatGPT to understand the file's in-built functions
     *
     * @param dirPath
     * @param filePath
     */

    public Prince(String dirPath, String filePath) {
        createDirectoryIfNotExists(dirPath);
        storage = new Storage(dirPath, filePath);
        ui = new Ui();
        parser = new Parser();
        taskList = new TaskList(storage.loadTasksFromFile());
    }

    /**
     * creates file directory if it does not exist
     * @param dirPath
     */

    private void createDirectoryIfNotExists(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("Directory has been created successfully: " + dirPath);
            } else {
                System.out.println("Failed to create directory: " + dirPath);
            }
        }
    }

    /**
     * Starts the application in the main method, comprises of the scanner to scan user inputs
     */

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String line = "";
        System.out.println("Hello! I'm Prince!");
        System.out.println("What would you like me to add to your TODO list today?");

        line = scanner.nextLine();
        getResponse(line);

        scanner.close();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            if (input.equals("bye")) {
                Storage.pushTasksToFile(TaskList.getList());
                return ui.terminationMessage();
            }
            String response = parser.parseConversation(input);
            assert response != null : "Response should not be null";
            Storage.pushTasksToFile(TaskList.getList());
            return response;
        } catch (IncompleteDescException | UnknownWordException | InvalidDeadlineException e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }

    /**
     * main method of the application that runs Prince
     * @param args
     */

    public static void main(String[] args) {
        new Prince("./data","data/prince.Prince.txt").run();
    }
}

