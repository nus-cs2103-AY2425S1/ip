package joe.Main;

import joe.exceptions.CorruptedFileException;
import joe.tasks.Task;
import joe.tasks.TaskList;

import java.io.FileNotFoundException;

public class Joe {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Joe(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = initializeTasks();
        } catch (FileNotFoundException | CorruptedFileException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    public TaskList initializeTasks() throws FileNotFoundException, CorruptedFileException {
        return new TaskList(storage.load());
    }

    public TaskList getTasks() {
        return tasks;
    }

    public void run() {
        ui.runProgramme(tasks);
    }

    public static void main(String[] args) {
        new Joe("src/main/data/joe.txt").run(); // remove ../ if running from the root directory
    }

}
