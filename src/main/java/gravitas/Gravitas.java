package gravitas;

import gravitas.exception.DukeException;
import gravitas.storage.Storage;
import gravitas.tasklist.TaskList;
import gravitas.ui.Ui;


/**
 * Represents the main class of the program.
 */
public class Gravitas {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Gravitas.
     *
     * @param filePath File path to load file
     */
    public Gravitas(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Runs the program.
     */
    public void run() {
        this.ui.greet();
        while (this.ui.display(tasks)) {
            try {
                this.storage.saveTask(this.tasks);
            } catch (DukeException e) {
                Ui.showLoadingError();
            }
        }
    }
    public static void main(String[] args) {
        new Gravitas("Documents/Github/IP/data/tasks.txt").run();
    }
}
