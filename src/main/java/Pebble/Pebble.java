package pebble;

import java.io.IOException;
import java.util.Scanner;

/**
 * A chatbot that helps to handle tasks.
 * Tasks including ToDos, Events, Deadlines.
 * Equipped with basic functionalities to manipulate the task list.
 */
public class Pebble {
    /** instance to handle user interface */
    private Ui ui;
    /** instance to handle local storage of tasks list */
    private Storage storage;
    /** A list to store tasks */
    private TasksList tasksList;

    /**
     * Initialises the whole program with the data file.
     *
     * @param filePath Data file that contains the tasks list.
     */
    public Pebble(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasksList = new TasksList(storage.loadTasks());
        } catch (IOException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            tasksList = new TasksList();
        }
    }

    /**
     * Starts running program
     */
    public void run() {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            Command command = Parser.parseCommand(input);
            command.execute(tasksList, ui, storage);
            if (command.getCommandType() == CommandType.BYE) {
                break;
            }
        }
    }

    /**
     * Entry point for program
     *
     * @param args (Unused as input is through file reading and keyboard)
     */
    public static void main(String[] args) {
        // launch with data file
        new Pebble("data/pebble.txt").run();
    }
}
