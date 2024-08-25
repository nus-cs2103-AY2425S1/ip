package gravitas;

import gravitas.exception.DukeException;
import gravitas.storage.Storage;
import gravitas.tasklist.TaskList;
import gravitas.ui.Ui;

import java.nio.file.Paths;

public class Gravitas {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Gravitas(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.showLoadingError();
        }
    }

    public void run() {
        this.ui.greet();
        while(this.ui.display(tasks)) {
            try {
                this.storage.saveTask(this.tasks);
            } catch (DukeException e) {
                ui.showLoadingError();
            }
        }
    }
    public static void main(String[] args) {
        new Gravitas("Documents/Github/IP/data/tasks.txt").run();
    }
}
