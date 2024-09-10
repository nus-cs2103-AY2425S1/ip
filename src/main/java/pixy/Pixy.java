package pixy;

import java.io.IOException;
import java.util.Scanner;

import pixy.parser.CommandType;
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

    /** Storage object to store the tasks */
    private Storage storage;

    /** Parser object to parse the user commands. */
    private Parser parser;

    /** Ui object to handle interaction between user and chatbot */
    private Ui ui;

    /** Scanner object for inputs */
    private Scanner sc;

    /** Command type string */
    private String commandType;

    /** CommandType object to call ComandType functions*/
    private CommandType c;

    /** File path */
    private static final String FILE_NAME = "./data/tasks.txt";

    /**
     * Creates Pixy object to start the chatbot.
     */
    public Pixy() {
        storage = new Storage(FILE_NAME);
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
            String response = parser.executeCommand(command, tasks, ui);
            CommandType type = parser.parseCommandType(command);
            if (type == CommandType.BYE) {
                ui.showGoodbyeMessage();
                break;
            }
            System.out.println(response); // Output the response to the user
        }
        saveTasks();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        String response = parser.executeCommand(input, tasks, ui);
        CommandType type = parser.parseCommandType(input);
        commandType = type != null ? type.getCommand() : "Unknown Command"; // Ensure non-null value
        return response;
    }

    /**
     * Returns the command type
     *
     */
    public String getCommandType() {
        return commandType;
    }

    public static void main(String[] args) {
        new Pixy().run();
    }
}
