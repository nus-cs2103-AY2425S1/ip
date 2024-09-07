package wenjiebot;

import java.util.Scanner;

import wenjiebot.commands.Command;
import wenjiebot.exceptions.WenJieException;

/**
 * The WenJie class represents the main application for the wenjiebot individual project.
 * It initializes the user interface, storage, and task list, and controls
 * the execution flow of the bot by processing user commands.
 */
public class WenJie {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a WenJie instance with the specified file path for storage.
     * Initializes the user interface, storage, and task list.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public WenJie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Starts the WenJie application, displaying a welcome message and
     * continuously processing user commands until the exit condition is met.
     * Handles any WenJieException that occurs during command execution.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(scanner);
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (WenJieException e) {
                ui.showError(e.getMessage());
            }
        }
        scanner.close();
    }

    /**
     * The main entry point of the WenJie application.
     * Creates a WenJie instance with the specified file path and starts the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        String filePath = "./src/main/java/data/wenjie.txt";
        new WenJie(filePath).run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        ui.showWelcome();
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (WenJieException e) {
            ui.showError(e.getMessage());
        }
        return ui.getOutput();
    }
}
