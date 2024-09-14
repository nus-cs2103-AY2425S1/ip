package cook;

import java.io.File;
import java.io.IOException;

import commands.Command;
import exceptions.InvalidCommandException;
import exceptions.InvalidInputException;

/**
 * Cook class to store main logic and program.
 */
public class Cook {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructor for Cook class.
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
     * Runs main logic.
     */
    public String getResponse(String input) {
        try {
            Command c = this.parser.readInput(input);
            assert c != null;
            return c.execute(this.tasks, this.ui, this.storage);
        } catch (InvalidCommandException | InvalidInputException e) {
            return e.getMessage();
        }
    }

    /**
     * Prints response of Cook given an input from the user (CLI)
     */
    public void run() {
        String response;
        this.ui.welcome();
        while (true) {
            String input = this.ui.getInput();
            response = this.getResponse(input);
            if (response.equals("Bye.")) {
                break;
            } else {
                this.ui.say(response);
            }
        }
    }

    /**
     * Runs main.
     */
    public static void main(String[] args) {
        new Cook(new File("data", "tasks.txt")).run();
    }
}
