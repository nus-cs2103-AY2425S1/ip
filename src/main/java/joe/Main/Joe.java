package joe.Main;

import joe.exceptions.CorruptedFileException;
import joe.tasks.TaskList;

import java.io.FileNotFoundException;

public class Joe {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for Joe.
     *
     * @param filePath the file path to retrieve and store the tasks
     */
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

    /**
     * Initializes the tasks from the file.
     *
     * @return a TaskList object containing the tasks
     * @throws FileNotFoundException  if the file is not found
     * @throws CorruptedFileException if the file is corrupted
     */
    public TaskList initializeTasks() throws FileNotFoundException, CorruptedFileException {
        return new TaskList(storage.load());
    }

    /**
     * Returns the TaskList object containing the tasks.
     *
     * @return a TaskList object containing the tasks
     */
    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Runs the programme.
     */
    public void run() {
        ui.runProgramme(tasks);
    }

    public static void main(String[] args) {
        new Joe("src/main/data/joe.txt").run(); // remove ../ if running from the root directory
    }

}
