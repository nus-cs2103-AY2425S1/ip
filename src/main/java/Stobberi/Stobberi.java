package Stobberi;

import Stobberi.Command.Command;
import Stobberi.StobberiException.StobberiException;
import Stobberi.components.Parser;
import Stobberi.components.Storage;
import Stobberi.components.TaskList;
import Stobberi.components.Ui;

/**
 * The Stobberi class is the main entry point for the task management application.
 * It initializes the required components, manages the main application loop, and
 * handles user input and task management.
 * <p>
 * The application allows users to manage tasks, save them to a file, and load them
 * upon restart. It provides a simple command-line interface for interacting with tasks.
 * </p>
 */
public class Stobberi {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a new Stobberi application instance.
     * Initializes the TaskList, Ui, and Storage components.
     */
    public Stobberi() {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage("data/list.txt");
    }

    /**
     * Runs the main application loop.
     * Loads tasks from storage, greets the user, processes user commands,
     * and saves tasks to storage before exiting.
     */
    public void run() {
        taskList.updateTaskList(storage.getList());
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand, taskList);
                c.execute();
                isExit = c.isExit();
            } catch (StobberiException e) {
                Ui.displayForm(e.getMessage());
            }
        }
        ui.goodbye();
        storage.saveList(taskList.getListOfTasks());
    }

    /**
     * The main method that starts the Stobberi application.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Stobberi().run();
    }
}
