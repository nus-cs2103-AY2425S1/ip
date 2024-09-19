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

    private static final String FILEPATH = "data/fanny.txt";

    /** Handles the storing and loading of tasks */
    private Storage storage;

    /**
     * Constructs a new Fanny object.
     */
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
        boolean shouldExit = false;

        while (!shouldExit) {
            try {
                String input = ui.getUserInput();
                ui.showHorizontalLine();
                Command c = Parser.parse(input);
                c.executeCmd(tasks, ui);
                shouldExit = c.shouldExit();
            } catch (FannyException e) {
                ui.showMessage(e.getMessage());
            } finally {
                ui.showHorizontalLine();
            }
        }
    }

    /**
     * Launches the Fanny application.
     *
     * @param args Arguments.
     */
    public static void main(String[] args) {
        new Fanny("./data/fanny.txt").run();
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input The input to be responded by the chatbot.
     * @return The response after processing the input.
     */
    public String generateResponse(String input) {
        String output = "";

        try {
            Command c = Parser.parse(input);
            output = c.executeCmd(tasks, ui);
        } catch (FannyException e) {
            output = ui.showMessage(e.getMessage());
        }

        return output;
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
