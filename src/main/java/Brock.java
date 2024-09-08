import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import commands.Command;
import exceptions.BrockException;
import parser.Parser;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

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
        TaskList tasks;
        try {
            ArrayList<Task> prevTasks = STORAGE.loadTasksFromFile();
            tasks = new TaskList(prevTasks);
            UI.displayResponse("Successfully read from save file!");
        } catch (FileNotFoundException e) {
            UI.displayResponse("Unable to find the save file!\n"
                    + "Please re-run the program and try again.");
            return;
        } catch (BrockException e) {
            // Save file was corrupted
            // Reset to blank file and proceed
            UI.displayResponse(e.getMessage());
            tasks = new TaskList(new ArrayList<>());
        }

        // Begin the interaction with chatbot
        UI.displayInitialResponse();
        boolean isExit = false;
        // Main loop
        while (!isExit) {
            String command = UI.readCommand(scanner);
            try {
                Command commandObj = PARSER.handleCommand(command);
                commandObj.execute(UI, STORAGE, tasks);
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

