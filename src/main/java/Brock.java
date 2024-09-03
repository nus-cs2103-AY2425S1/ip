import Exceptions.BrockException;

import Parser.Parser;
import Storage.Storage;
import Tasks.Task;
import Tasks.TaskList;
import Ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import Commands.Command;

/**
 * Class that represents the chatbot.
 */
public class Brock {
    private static final Ui UI = new Ui();
    private static final Storage STORAGE = new Storage();
    private static final Parser PARSER = new Parser();

    /**
     * Main logic of the chatbot.
     */
    public void run() {
        // Create a scanner object
        // Reads from standard system input
        Scanner scanner = new Scanner(System.in);

        // Creates the save file if necessary
        try {
            String[] result = STORAGE.createFile();
            UI.displayResponse(result[0]);
            UI.displayResponse(result[1]);
        } catch (IOException e) {
            UI.displayResponse("Error creating file!\n"
                    + "Please re-run the program and try again.");
            return;
        }

        // Initialize the ArrayList
        // With any pre-existing tasks from save file
        final TaskList TASKS;
        try {
            ArrayList<Task> prevTasks = STORAGE.loadTasksFromFile();
            TASKS = new TaskList(prevTasks);
        } catch (BrockException e) {
            UI.displayResponse(e.getMessage());
            return;
        }

        // Begin the interaction with chatbot
        UI.displayInitialResponse();
        boolean isExit = false;
        // Main loop
        while (!isExit) {
            String command = UI.readCommand(scanner);
            try {
                Command commandObj = PARSER.handleCommand(command);
                commandObj.execute(UI, STORAGE, TASKS);
                isExit = commandObj.isExit();
            } catch (BrockException e) {
                UI.displayResponse(e.getMessage());
            }
        }
    }

    /**
     * Main method, when called, runs the main logic of the chatbot.
     * @param args The command line arguments.
     **/
    public static void main(String[] args) {
        new Brock().run();
    }
}

