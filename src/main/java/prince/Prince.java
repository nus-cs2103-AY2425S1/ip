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

        System.out.println("Hello! I'm prince.Prince!");
        System.out.println("What would you like me to add to your TODO list today?");


        while(!line.equals("bye")) {
            line = scanner.nextLine();
            try {
                if(line.equals("bye")) {
                    ui.terminationMessage();
                    break;
                }
                String s = parser.parseConversation(line);
                System.out.println(s);
                System.out.println("How else would you like me to edit your TODO list today?");
                Storage.pushTasksToFile(TaskList.getList());
            } catch (IncompleteDescException | UnknownWordException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
        scanner.close();
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

