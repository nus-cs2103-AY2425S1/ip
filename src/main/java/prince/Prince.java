package prince;

import exception.IncompleteDescException;
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

    //private static final String dirPath = "./data";
    //private static final String filePath = dirPath + File.separator + "prince.txt";
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

    /**
     * Constructor of Prince
     *
     * Initialises the various components and creates the files necessary for storage
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
        //return "Duke heard: " + input;
        try {
            if(input.equals("bye")) {
                return ui.terminationMessage();
            }
            String response = parser.parseConversation(input);
            Storage.pushTasksToFile(TaskList.getList());
            return response;
            //System.out.println("How else would you like me to edit your TODO list today?");
        } catch (IncompleteDescException | UnknownWordException e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }

    /**
     * main method of the application that runs Prince
     * @param args
     */

    public static void main(String[] args) {
        new Prince("./data","data/prince.Prince.txt").run();

        //createListFile();
        //loadTasksFromFile(filePath);
        // if any words, repeat scanning, but the moment the word is bye,
        // then exit and print bye

        /*try {
            System.out.println(conversation(line));
        } catch (exception.IncompleteDescException e) {
            System.out.println(e.getMessage());
        } catch (exception.UnknownWordException e) {
            System.out.println(e.getMessage());
        }*/
    }
}

