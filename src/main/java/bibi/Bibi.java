package bibi;

import bibi.task.TaskList;
import java.io.FileNotFoundException;

public class Bibi {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    private boolean isExit;

    public Bibi(String filePath) {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage(filePath);
        this.isExit = false;

        // Init
        ui.printWelcomeMessage();
        storage.initializeDataDirectory();
        try {
            storage.restoreTasks(tasks);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        ui.printHorizontalLine();
    }

    public void run() {
        while (!isExit) {
            Command cmd = Parser.parseCommand(ui.readInput());
            cmd.execute(tasks, ui, storage);
            isExit = cmd.isExit();
        }
    }
    public static void main(String[] args) {
        // Insert path to saveFile
        new Bibi("data/list2.txt").run();
    }
}
