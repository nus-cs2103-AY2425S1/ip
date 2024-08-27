package fishman;

import fishman.command.Command;
import fishman.exception.FishmanException;
import fishman.utils.Parser;
import fishman.utils.Storage;
import fishman.task.TaskList;
import fishman.utils.Ui;

/**
 * The main class for the Fishman bot.
 * This class initializes the user interface, task list and parser,
 * and manages the main program.
 */
public class Fishman {
    /** The user interface object for handling input and output operations. */
    private final Ui ui;
    /** The task list object to store and manage tasks. */
    private TaskList tasks;
    /** The storage object used to handle file operations. */
    private final Storage storage;

    /**
     * Constructs a new instance of Fishman
     * Initializes the UI, task list and parser.
     */
    public Fishman() {
        ui = new Ui();
        tasks = new TaskList();

        storage = new Storage("./data/fishman.csv");
    }

    /**
     * Starts the Fishman bot.
     * Displays the logo and welcome message, before entering the main loop.
     * The loop will continue until the exit command is received.
     * The method will handle exceptions that may occur during execution.
     * Any unchecked exception is caught and reported as well.
     */
    public void start() {
        ui.displayLogo();
        ui.displayWelcome();
        boolean isExit = false;
        try {
            tasks = storage.load();
        } catch (FishmanException e) {
            ui.displayError(e.getMessage());
        }
        while (!isExit) {
            try {
                String userInput = ui.readCommands();
                Command command = Parser.parse(userInput, tasks);
                command.execute(tasks, ui);
                isExit = command.isExit();
            } catch (FishmanException e) {
                ui.displayError(e.getMessage());
            } catch (Exception e) {
                ui.displayError("Uh oh, an unexpected error has occured: " + e.getMessage());
            }
        }
        storage.save(tasks);
    }

    /**
     * The main entry point for Fishman bot.
     * Creates a new Fishman instance and starts the bot.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) throws FishmanException {
        new Fishman().start();
    }
}

