package dude;

import java.io.IOException;

import command.Command;
import exception.DudeException;
import parser.Parser;
import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * The Dude class is a chatbot, whose name is Dude, that can help you manage your tasks.
 */
public class Dude {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Dude object with the given file path.
     *
     * @param filePath The file path of the storage file.
     */
    public Dude(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.initTasks();
        } catch (IOException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        } catch (DudeException e) {
            this.ui.showError(e.getMessage());
        }
    }

    /**
     * Creates a Dude object with the default file path.
     */
    public Dude() {
        this("data.txt");
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        this.ui.showGreeting();
        boolean isExit = false; // Flag to exit the chatbot when "bye" is entered
        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DudeException e) {
                this.ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Returns the response of the chatbot to the user input.
     *
     * @param input The user input.
     * @return The response of the chatbot to the user input.
     */
    public String getResponse(String input) {
        String output;
        try {
            Command c = Parser.parse(input);
            c.execute(this.tasks, this.ui, this.storage);
            output = this.ui.getResponse();
        } catch (DudeException e) {
            output = e.getMessage();
        }
        return "Dude: " + output;
    }

    public static void main(String[] args) {
        new Dude("data.txt").run();
    }
}
