package milo;

import milo.command.Command;
import milo.parser.Parser;
import milo.storage.Storage;
import milo.tasks.TaskList;
import milo.ui.Ui;

/**
* Represents the task bot programme
* containing components such as
* Storage, TaskList, Ui
 */
public class Milo {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
    * Initialise Milo bot and its Ui, Storage, TaskList field
     */
    public Milo() {
        this.ui = new Ui();
        this.storage = new Storage("./src/data/miloData.txt");
        // Reads data from storage
        this.tasks = new TaskList(storage.readData());
        this.parser = new Parser();
    }

    /**
    * Main method
     */
    public static void main(String[] args) {
        new Milo().run();
    }


    /**
     * Method running Milo
     */
    public void run() {
        ui.greetUser();
        runCommandLoopTilBye();
    }

    /**
     * Method looping for user input
     */
    public void runCommandLoopTilBye() {
        String input;
        do {
            input = ui.getUserInput();
            parser.readInput(input, this.tasks);
            // Save data to storage
        } while (!input.toLowerCase().strip().equals("bye"));
        storage.saveData(this.tasks);
    }

    /**
     * method to return Milo response from user input
     *
     * @param input user text input
     */
    public String getResponse(String input) {
        // Check if input equals to bye
        if (input.toLowerCase().strip().equals("bye")) {
            // Save data to storage
            storage.saveData(this.tasks);
        }
        Command userCommand = parser.readInput(input, this.tasks);
        userCommand.execute(this.tasks);
        return userCommand.commandToString(this.ui, this.tasks);
    }
}
