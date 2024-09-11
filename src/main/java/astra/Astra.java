package astra;

import astra.command.Command;

/**
 * Astra is a chatbot for task management.
 */
public class Astra {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructor for Astra.
     *
     * @param path The path to the file where the tasks are stored.
     */
    public Astra(String path) {
        this.ui = new Ui();
        this.storage = new Storage(path);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (AstraException e) {
            ui.showError(e);
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        boolean isExit = false;
        ui.greet();

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (AstraException e) {
                ui.showError(e);
            }
        }

        ui.printGoodbye();
        ui.stop();
    }

    /**
     * Gets the response from the chatbot.
     *
     * @param input The input from the user.
     * @return The response from the chatbot.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            assert c != null : "Command should not be null";
            return c.execute(tasks, storage);
        } catch (AstraException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Astra("./data").run();
    }
}
