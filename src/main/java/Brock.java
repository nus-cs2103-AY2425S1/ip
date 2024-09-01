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

public class Brock {
    private final Ui UI = new Ui();
    private final Storage STORAGE = new Storage();
    private final Parser PARSER = new Parser();

    public void run() {
        // Create a scanner object
        // Reads from standard system input
        Scanner scanner = new Scanner(System.in);

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

    public static void main(String[] args) {
        new Brock().run();
    }
}

