package dynamike;

import java.io.IOException;

import command.Command;
import exception.DynamikeException;
import parser.Parser;
import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * The Dynamike class is a chatbot, whose name is Dynamike, that can help you manage your tasks.
 */
public class Dynamike {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Dynamike object with the given file path.
     *
     * @param filePath The file path of the storage file.
     */
    public Dynamike(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.initTasks();
        } catch (IOException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        } catch (DynamikeException e) {
            this.ui.showError(e.getMessage());
        }
    }

    /**
     * Creates a Dynamike object with the default file path.
     */
    public Dynamike() {
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
            } catch (DynamikeException e) {
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
        } catch (DynamikeException e) {
            output = e.getMessage();
        }
        return output;
    }

    public static void main(String[] args) {
        new Dynamike("data.txt").run();
    }
}
