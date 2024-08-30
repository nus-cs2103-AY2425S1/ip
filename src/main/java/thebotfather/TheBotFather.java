package thebotfather;

import thebotfather.command.Command;
import thebotfather.task.Task;
import thebotfather.util.*;

import java.util.ArrayList;

/**
 * The main class for TheBotFather application.
 * TheBotFather is a task management application that allows users to manage their tasks
 * through a command-line interface.
 * <p>
 * The class initializes the necessary components for the application, such as
 * storage, task list, and user interface, and runs the main loop of the program.
 */
public class TheBotFather {

    /**
     * The storage instance used to read from and write to the data file.
     */
    private final Storage storage;

    /**
     * The task list containing the current tasks.
     */
    private TaskList taskList;

    /**
     * The user interface instance used to interact with the user.
     */
    private final Ui ui;

    /**
     * Constructs a new TheBotFather instance.
     *
     * @param filePath The file path where the task data is stored.
     */
    public TheBotFather(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(new ArrayList<Task>());
        try {
            taskList = storage.load();
        } catch (TheBotFatherException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Runs the main loop of TheBotFather application.
     * Continuously reads user input, processes commands, and executes them
     * until the exit command is issued.
     */
    public void run() {
        boolean isExit = false;
        ui.printGreeting();
        while (!isExit) {
            try {
                String completeLine = ui.readCommand();
                Command command = Parser.parse(completeLine, ui);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (TheBotFatherException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    /**
     * The main method to start TheBotFather application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {

        new TheBotFather("./data/TheBotFather.txt").run();

    }
}
