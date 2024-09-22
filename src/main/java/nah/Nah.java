package nah;

import nah.command.Command;
import nah.exceptions.NahException;
import nah.parser.Parser;
import nah.storage.Storage;
import nah.tasklist.TaskList;
import nah.ui.UI;

/**
 * Our chatBot
 */
public class Nah {
    private Storage storage;
    private UI ui;
    private TaskList tasks;

    /**
     * Constructor for the chatBot
     */
    public Nah() {
        ui = new UI();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());

        } catch (NahException e) {
            ui.show(e.getMessage());
            storage = new Storage();
            tasks = new TaskList();
        }
    }

    /**
     * Builds Nah chatBot based on a file of tasks.
     *
     * @param filePath the file of tasks
     */
    public Nah(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());

        } catch (NahException e) {
            ui.show(e.getMessage());
            storage = new Storage();
            tasks = new TaskList();
        }

    }

    /**
     * creates response for the chatBot from the input
     * @param input the input
     * @return the response
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (NahException e) {
            return e.getMessage();
        }
    }

    /**
     * main method
     * @param args
     */
    public static void main(String[] args) {

        Nah nah = new Nah();

    }
}
