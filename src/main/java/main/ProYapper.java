package main;

import javafx.scene.layout.VBox;
import storage.Storage;
import tasks.Task;
import ui.Ui;

import java.util.List;

/**
 * Main class for the ProYapper application.
 * This class initializes the application, processes user input, and manages the task list.
 */
public class ProYapper {
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    private final Parser parser;

    /**
     * Constructs a {@code ProYapper} instance with the specified file path for storage.
     *
     * @param filePath the file path to load and save tasks
     */
    public ProYapper(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        List<Task> tasks = storage.loadTasks();
        this.taskList = new TaskList(tasks);
    }

    public void setDialogContainer(VBox dialogContainer) {
        ui.setDialogContainer(dialogContainer);
    }
}