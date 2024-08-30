package bimo;
import bimo.command.Command;

/**
 * Represents a chatbot class.
 */
public class Bimo {
    public static String NAME = "bimo";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Returns an instance of a chatbot.
     *
     * @param filePath File path of file containing list of tasks.
     */
    public Bimo(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadFile());
        } catch (BimoException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Starts the chatbot up.
     */
    public void run() {
        ui.greetUser(NAME);
        boolean isRunning = true;
        while (isRunning) {
            try {
                String input = ui.getUserCommand();
                ui.showLine();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isRunning = !c.getIsQuit();
            } catch (BimoException e) {
                ui.showErrorMessage();
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Bimo("data/Bimo.txt").run();
    }
}

