package orion.chatbot;

import orion.commands.Command;
import orion.exceptions.OrionException;

/**
 * The Orion class represents the main chatbot application.
 * It handles the initialization of the UI, storage, and task list,
 * and contains the main logic for running the chatbot.
 */
public class Orion {

    /**
     * Indicates whether the chatbot is online and should continue running.
     */
    private boolean isOnline;

    /**
     * The UI component responsible for handling user interaction.
     */
    private Ui ui;

    /**
     * The storage component responsible for loading and saving tasks.
     */
    private Storage storage;

    /**
     * The task list that holds the tasks loaded from storage or created during runtime.
     */
    private TaskList tasks;

    /**
     * Constructs an Orion chatbot instance.
     * Initializes the UI, storage, and task list.
     * Greets the user and loads tasks from storage.
     * If tasks cannot be loaded, initializes an empty task list.
     */
    public Orion() {
        isOnline = true;
        ui = new Ui();
        storage = new Storage();

        ui.printGreet();

        try {
            tasks = new TaskList(storage.loadTasks());
            ui.printLoadTasks(tasks.getNoTasks());
        } catch (OrionException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        } finally {
            ui.printBar();
        }
    }

    /**
     * Starts the main loop of the chatbot.
     * Continuously reads and executes commands from the user until the exit command is given.
     */
    public void run() {
        while (isOnline) {
            try {
                String command = ui.readCommand();
                Command c = Parser.parse(command);
                c.execute(tasks, storage, ui);
                isOnline = !c.isExit();
            } catch (OrionException e) {
                ui.printErrorMessage(e.getMessage());
            } catch (Exception e) {
                ui.printErrorMessage("Unexpected error! " + e.getMessage());
            } finally {
                ui.printBar();
            }
        }

        ui.closeScanner();
    }

    /**
     * The main method that starts the Orion chatbot application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Orion().run();
    }
}