package optimus;

import optimus.commands.Command;
import optimus.exceptions.OptimusExceptions;

/**
 * Execution class
 */
public class Optimus {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructor creates a Storage, TaskList and UI
     */
    public Optimus() {
        this.storage = new Storage();
        this.tasks = new TaskList(storage.load());
        this.ui = new Ui();
    }

    /**
     * Boots up the UI and keeps running until the UI decides to shut down
     */
    private void run() {
        ui.greet();
        boolean runChat = true;
        while (runChat) {
            try {
                String input = ui.readCommand();
                ui.showLineBreak();
                Command c = Parser.parse(input);
                c.execute(storage, tasks, ui);
                runChat = c.shouldContinue();
            } catch (OptimusExceptions e) {
                System.out.println(e.getMessage());
            } finally {
                ui.showLineBreak();
            }
        }
    }

    public static void main(String[] args) {
        Optimus optimus = new Optimus();
        optimus.run();
    }
}
