package fishman;

import fishman.command.Command;
import fishman.exception.FishmanException;
import fishman.task.TaskList;
import fishman.utils.Parser;
import fishman.utils.Storage;
import fishman.utils.Ui;
import javafx.application.Platform;
import javafx.util.Pair;

import java.util.concurrent.TimeUnit;

/**
 * The main class for the Fishman bot.
 * This class initializes the user interface, task list and parser,
 * and manages the main program.
 */
public class Fishman {

    /** The task list object to store and manage tasks. */
    private TaskList tasks = new TaskList();
    /** The storage object used to handle file operations. */
    private final Storage storage = new Storage("./data/fishman.csv");
    private final Ui ui = new Ui();

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input, tasks);
            if (command.isExit()) {
                saveTasks();
                Platform.exit();
            }
            return command.execute(tasks, ui);
        } catch (FishmanException e) {
            return e.getMessage();
        }
    }

    public String loadTasks() {
        try {
            Pair<TaskList, String> loadResult = storage.load();
            tasks = loadResult.getKey();
            return loadResult.getValue();
        } catch (Exception e) {
            return "An unexpected error has occurred: " + e.getMessage();
        }
    }

    public void saveTasks() throws FishmanException {
        try {
            storage.save(tasks);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}

