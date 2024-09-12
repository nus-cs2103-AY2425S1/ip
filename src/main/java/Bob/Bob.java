package bob;

import bob.storage.Storage;
import bob.tasklist.TaskList;
import bob.ui.Ui;
import bob.parser.Parser;
import bob.command.Command;
import bob.exception.BobException;

import java.util.Scanner;

/**
 * The main class for Bob chatbot.
 * Handles the initialization and interactions of the chatbot.
 */
public class Bob {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a Bob instance with a specified file path for storage.
     * @param filePath The file path where tasks are stored.
     */
    public Bob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (BobException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    // Constructor without argument
    public Bob() {
        this("./data/bob.csv");
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        StringBuilder response = new StringBuilder();
        try {
            Command command = parser.parse(input);
            response.append(command.execute(tasks.getTasks(), storage, ui));
            return response.toString();
        } catch (BobException e) {
            return e.getMessage();
        }
    }

    /**
     * Starts Bob chatbot, handling user input and executing commands.
     * This method is primarily for console-based interaction.
     */
    public void run() {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            input = scanner.nextLine().trim();
            try {
                Command command = parser.parse(input);
                command.execute(tasks.getTasks(), storage, ui);
            } catch (BobException e) {
                ui.showError(e.getMessage());
            }
        } while (!input.equals("bye"));
        ui.showGoodbye();
        scanner.close();
    }

    /**
     * The main entry point of Bob chatbot.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        new Bob("./data/bob.csv").run();
    }
}
