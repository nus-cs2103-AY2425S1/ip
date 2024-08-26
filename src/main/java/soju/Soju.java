package soju;

import soju.commands.ByeCommand;
import soju.commands.Command;

import java.util.Scanner;

/**
 * Represents the Soju chatbot application.
 * It handles the initialization of the application, loading tasks from storage,
 * and running the main event loop to process user commands.
 */
public class Soju {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructs a Soju object and initializes its components.
     * It loads tasks from the specified storage file and initializes
     * the TaskList. If an error occurs during loading, a new TaskList is created.
     */
    public Soju() {
        ui = new Ui();
        storage = new Storage("./data/soju.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (SojuException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * The main entry point of the Soju application.
     * It creates a Soju instance and starts the chatbot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Soju soju = new Soju();
        soju.run();
    }

    /**
     * Runs the main event loop of the Soju chatbot.
     * It greets the user, continuously reads and processes user commands,
     * saves tasks to storage, and exits when the ByeCommand is executed.
     */
    public void run() {
        ui.greet();
        while (true) {
            try {
                String userInput = ui.readCommand();
                Command command = Parser.parse(userInput);
                command.execute(tasks, ui, storage);
                ui.printHorizontalLine();
                storage.saveToFile(tasks);
                if (command instanceof ByeCommand) {
                    break;
                }
            } catch (SojuException e) {
                ui.printError(e);
            }
        }
    }
}
