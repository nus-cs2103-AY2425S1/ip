package reminderebot;
import reminderebot.commands.Command;
import reminderebot.task.Task;

import java.util.ArrayList;

public class Reminderebot {
    // note: needs support for handling multi-line inputs
    private Storage storage;
    private TaskList tasklist;
    private Ui ui;

    /**
     * Entry point to program.
     * @param args
     */
    public static void main(String[] args) {
        new Reminderebot("./data/Reminderebot.txt").run();
    }

    /**
     * Initialise Reminderebot.
     * @param filePath
     */
    public Reminderebot(String filePath) {
        this.ui = new Ui();
        this.storage =  new Storage(filePath);
        try {
            this.tasklist = new TaskList(storage.readFileContents());
        } catch (ReminderebotException e) {
            this.tasklist = new TaskList(new ArrayList<Task>());    // in case file not found
            System.out.println(e);
        }
    }

    /**
     * Run Reminderebot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasklist, ui, storage);
                isExit = c.isExit();
            } catch (ReminderebotException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}