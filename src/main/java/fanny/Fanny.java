package fanny;

import fanny.command.Command;
import fanny.parser.Parser;
import fanny.storage.Storage;
import fanny.task.TaskList;
import fanny.ui.Ui;

/**
 * The main class for the Fanny task management chatbot.
 * Fanny is a text-based task manager chatbot that allows users
 * to manage tasks, including to-dos, deadlines, and events.
 */
public class Fanny {

    /** List of tasks */
    private static TaskList tasks;

    /** Handles user interface interaction */
    private static Ui ui;

    /** Handles the storing and loading of tasks */
    private Storage storage;

    private static final String FILEPATH = "data/fanny.txt";

    public Fanny() {
        this(FILEPATH);
    }
    /**
     * Constructs a new Fanny object with the specified file path for storage.
     *
     * @param filePath The path of the file where tasks are stored.
     */
    public Fanny(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage);
    }

    /**
     * Starts the Fanny chatbot and runs the main event loop.
     */
    public void run() {
        ui.printHello();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.getUserInput();
                ui.showHorizontalLine();
                Command c = Parser.parse(input);
                c.executeCmd(tasks, ui);
                isExit = c.shouldExit();
            } catch (FannyException e) {
                ui.showMessage(e.getMessage());
            } finally {
                ui.showHorizontalLine();
            }
        }
    }

    /**
     * The main method to launch Fanny.
     *
     * @param args Arguments.
     */
    public static void main(String[] args) {
        new Fanny("./data/fanny.txt").run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String generateResponse(String input) {
        String capturedOutput = "";

        try {
            Command c = Parser.parse(input);
            capturedOutput = c.executeCmd(tasks, ui);
        } catch (FannyException e) {
            capturedOutput = ui.showMessage(e.getMessage());
        }

        return capturedOutput;
    }

    /**
     * Retrieves reminders for tasks with upcoming deadlines.
     *
     * @return A string of upcoming deadlines formatted for display.
     */
    public static String getReminders() {
        return ui.showReminders(tasks);
    }

}
