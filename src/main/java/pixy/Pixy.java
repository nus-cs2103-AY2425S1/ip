package pixy;

import java.io.IOException;
import java.util.Scanner;

import pixy.parser.Parser;
import pixy.storage.Storage;
import pixy.tasks.TaskList;
import pixy.ui.Ui;

/**
 * Represents the task manager chatbot Pixy.
 */
public class Pixy {

    /** List of tasks */
    private TaskList tasks;

    /** storage object to store the tasks */
    private Storage storage;

    /** Parser object to parse the user commands. */
    private Parser parser;

    /** Ui object to handle interaction between user and chatbot*/
    private Ui ui;

    /** Scanner object for inputs*/
    private Scanner sc;

    /**
     * Creates Pixy object to start the chatbot.
     *
     * @param filePath The specified filePath to store the tasks.
     */
    public Pixy(String filePath) {
        storage = new Storage(filePath);
        parser = new Parser();
        ui = new Ui();
        sc = new Scanner(System.in);

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Takes in user commands as inputs.
     *
     * @return A string representation of the user's commands.
     */
    private String inputTask() {
        return sc.nextLine();
    }

    /**
     * Saves the tasks into the hard disk.
     */
    private void saveTasks() {
        try {
            storage.save(tasks.getList());
        } catch (IOException e) {
            ui.showError("An error occurred while saving tasks.");
        }
    }

    /**
     * Runs the chatbot and handles user inputs.
     */
    public void run() {
        ui.showWelcomeMessage();

        while (true) {
            String command = inputTask();
            boolean exit = parser.parseCommand(command, tasks, ui);
            if (exit) {
                ui.showGoodbyeMessage();
                break;
            }
        }
        saveTasks();
    }
    public static void main(String[] args) {
        new Pixy("./data/tasks.txt").run();
    }
}
