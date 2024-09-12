package cook;

import java.io.File;
import java.util.HashMap;

import commands.Command;
import exceptions.InvalidCommandException;
import exceptions.InvalidInputException;

/**
 * Cook class to store main logic and program.
 */
public class Cook {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for Cook class.
     */
    public Cook(File file) {
        this.storage = new Storage(file);
        this.tasks = new TaskList();
        this.ui = new Ui();

        HashMap<String, Integer> validCommandArgs = new HashMap<>();

        validCommandArgs.put("bye", 1);
        validCommandArgs.put("list", 1);
        validCommandArgs.put("mark", 2);
        validCommandArgs.put("unmark", 2);
        validCommandArgs.put("delete", 2);
        validCommandArgs.put("find", 2);
        validCommandArgs.put("todo", 2);
        validCommandArgs.put("deadline", 4);
        validCommandArgs.put("event", 6);

        this.parser = new Parser(validCommandArgs);
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
