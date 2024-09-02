package mahesh;

import java.util.Scanner;

import mahesh.command.Command;
import mahesh.util.MaheshException;
import mahesh.util.Parser;
import mahesh.util.Storage;
import mahesh.util.TaskList;
import mahesh.util.Ui;

/**
 * Represents the main application class for managing tasks.
 * It initializes the application, processes user input, and executes commands.
 */
public class Mahesh {

    /**
     * The relative path to the file used for storing tasks.
     */
    private String path;

    /**
     * Constructs a Mahesh object with the specified file path for storing tasks.
     *
     * @param path the relative path to the file for storing tasks
     */
    public Mahesh(String path) {
        this.path = path;
    }

    /**
     * Runs the main application loop, processing user input and executing commands until an exit command is received.
     * Initializes the UI, Storage, TaskList, and Scanner.
     * Continuously reads user input, parses it into commands, executes the commands, and updates the storage.
     * Catches and prints any MaheshException errors that occur during command execution.
     */
    public void run() {
        Ui.printStartupMessage();
        Storage store = new Storage(this.path);
        TaskList list = store.retrieveData();
        Scanner scan = new Scanner(System.in);
        boolean exit = false;
        
        while (!exit) {
            String originalInput = scan.nextLine();
            Parser parserObj = new Parser(list, store);
            try {
                Command command = parserObj.parse(originalInput);
                if (command != null) {
                    command.execute();
                    exit = command.isExit();
                }
            } catch (MaheshException err) {
                Ui.printErr(err);
            }
            store.updateData(list);
        }
        scan.close();
    }

    /**
     * The main method to start the Mahesh application.
     * It initializes the Mahesh object with the specified file path and runs the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Mahesh app = new Mahesh("../../../../data/mahesh.txt");
        app.run();
    }
}
