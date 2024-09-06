package karen.main;

import karen.commands.Command;
import karen.tasks.TaskList;
import karen.util.Parser;
import karen.util.Storage;
import karen.util.Ui;

/**
 * The main class containing the logic of Karen
 */
public class Karen {
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor to initialize the <code>TaskList</code>, <code>Ui</code> and <code>Storage</code>
     */
    public Karen() {
        this.taskList = new TaskList();
        Storage.initFile();
        Storage.readFile(this.taskList);
        this.ui = new Ui();
    }

    /**
     * Starts the main loop
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String commandString = this.ui.readInput();
                Command c = Parser.parse(commandString, this.ui);
                if (c != null) { //c is null when input is invalid
                    c.execute(this.taskList, this.ui);
                    isExit = c.isExit();
                }
            } catch (Exception e) {
                System.out.println("Exception occurred in main loop");
                System.out.println(e);
                return;
            }
        }
    }

    public static void main(String[] args) {
        new Karen().run();
    }
}
