package luna;

import luna.command.Command;
import luna.command.ExitCommand;

/**
 * Represents a chatbot that allows users to add, remove and manipulate tasks.
 */
public class Luna {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a chatbot session
     */
    public Luna() {
        this.storage = new Storage();
        this.ui = new Ui();

        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (LunaException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot until exit command is entered
     */
    public String run(String input) {

        try {
            ui.showLine();
            Command command = Parser.parse(input);
            return command.execute(tasks, storage);

        } catch (LunaException e) {
            ui.showError(e.getMessage());
            return e.getMessage();
        } finally {
            ui.showLine();
        }
    }

    public static void main(String[] args) {
        new Luna().run("Luna started");
    }
}
