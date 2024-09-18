package cook;

import java.io.File;
import java.io.IOException;

import commands.Command;
import exceptions.InvalidCommandException;
import exceptions.InvalidInputException;

/**
 * Cook class to store main logic of chatbot.
 */
public class Cook {
    private static boolean isExit = false;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs Cook object.
     *
     * @param file Relative file location to store tasks.
     */
    public Cook(File file) {
        this.storage = new Storage(file);
        try {
            this.tasks = this.storage.readFile();
        } catch (IOException | ClassNotFoundException e) {
            this.tasks = new TaskList();
        }
        this.ui = new Ui();
        this.parser = new Parser();
    }

    /**
     * Returns chatbot's response to the input.
     *
     * @param input String input from the user.
     * @return String response.
     */
    public String getResponse(String input) {
        try {
            Command c = this.parser.readInput(input);
            assert c != null;
            return c.execute(this.tasks, this.storage);
        } catch (InvalidCommandException | InvalidInputException e) {
            return e.getMessage();
        }
    }

    /**
     * Prints responses from Cook to the CLI.
     */
    public void run() {
        this.ui.welcome();
        while (!isExit) {
            String input = this.ui.getInput();
            String response = this.getResponse(input);
            this.ui.say(response);
        }
    }

    /**
     * Tells Cook to exit.
     */
    public static void exit() {
        isExit = true;
    }

    /**
     * Creates a new Cook chatbot and runs it.
     */
    public static void main(String[] args) {
        new Cook(new File("data", "tasks")).run();
    }
}
