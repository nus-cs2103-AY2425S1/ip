package skibidi;

import java.io.IOException;
import java.util.Scanner;

import storage.TaskStorage;

/**
 * Represents the main class of the Skibidi program.
 */
public class Skibidi {
    private final Ui ui;
    private final Parser parser;
    private TaskStorage storage;

    /**
     * Creates a new Skibidi program.
     */
    public Skibidi() {
        ui = new Ui();
        parser = new Parser();
        try {
            storage = new TaskStorage("data/tasks.txt");
        } catch (IOException e) {
            ui.printMessage("Error loading tasks: " + e.getMessage());
            storage = null;
        }
    }

    /**
     * Runs the Skibidi program.
     */
    public void run() {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            try {
                String userInput = scanner.nextLine();
                Command command = parser.parse(userInput);
                isRunning = command.execute(ui, storage);
            } catch (SkibidiException e) {
                ui.printMessage(e.getMessage());
            }
        }

        scanner.close();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            command.execute(ui, storage);
        } catch (SkibidiException e) {
            e.getMessage();
        }
    }
}
