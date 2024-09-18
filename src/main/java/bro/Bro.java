package bro;

import bro.command.Command;
import bro.parser.Parser;
import bro.storage.Storage;
import bro.task.TaskList;
import bro.ui.UI;

/**
 * Represents a CLI chatbot named bro that helps you track your tasks
 */
public class Bro {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    /**
     * Returns a Bro instance which loads its initial task from a given file path
     *
     * @param filePath The file path containing the initial state of the task list tracked by Bro
     */
    public Bro(String filePath) {
        this.ui = new UI();
        this.storage = new Storage(filePath);
        try {
            tasks = storage.loadFromStorage();
        } catch (BroException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Begins running the Bro chatbot on the CLI
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                UI.showLine();
                Command c = Parser.parse(fullCommand, this.tasks, this.storage);
                c.execute(this.ui);
                isExit = c.isExit();
            } catch (BroException e) {
                this.ui.showError(e.getMessage());
            } finally {
                UI.showLine();
            }
        }
        assert isExit;
    }

    /**
     * Processes user input and returns a response from Bro
     * @param input
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input, this.tasks, this.storage);
            return c.execute(this.ui);
        } catch (BroException e) {
            return this.ui.showError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Bro("data/tasks.txt").run();
    }
}
